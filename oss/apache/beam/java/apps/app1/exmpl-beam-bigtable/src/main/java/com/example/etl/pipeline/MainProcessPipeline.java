package com.example.etl.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.etl.dto.StudentDto;
import com.example.etl.pipeline.subpipeline.SubStreamPipeline;
import com.google.cloud.bigtable.beam.CloudBigtableIO;
import com.google.cloud.bigtable.beam.CloudBigtableTableConfiguration;
import com.google.cloud.bigtable.hbase.BigtableOptionsFactory;

public class MainProcessPipeline {

    public static void main(String[] args) {
        BigtableOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(BigtableOptions.class);
        options.setStreaming(true);
        Pipeline p = Pipeline.create(options);
        CloudBigtableTableConfiguration bigtableTableConfig = new CloudBigtableTableConfiguration.Builder()
                .withProjectId(options.getBigtableProjectId())
                .withInstanceId(options.getBigtableInstanceId())
                .withTableId(options.getBigtableTableId())
                .build();
        Pipeline p2 = Pipeline.create();

        p.apply("Read PubSub",
                PubsubIO.readStrings().fromSubscription("projects/aspf-jp-test/subscriptions/test-bigtable-beam-sub"))
                .apply(ParDo.of(new DoFn<String, StudentDto>() {
                    @ProcessElement
                    public void processElement(@Element String json, OutputReceiver<StudentDto> out) throws Exception {
                        ObjectMapper mapper = new ObjectMapper();
                        StudentDto dto = mapper.readValue(json, StudentDto.class);
                        out.output(dto);
                    }
                }))
                .apply(new SubStreamPipeline());
                
        p.run().waitUntilFinish();
    }

    public interface BigtableOptions extends DataflowPipelineOptions {

        @Default.String("aspf-jp-test")
        String getProjectId();

        void setProjectId(String projectId);

        @Description("The Bigtable project ID, this can be different than your Dataflow project")
        @Default.String("fake")
        String getBigtableProjectId();

        void setBigtableProjectId(String bigtableProjectId);

        @Description("The Bigtable instance ID")
        @Default.String("fake")
        String getBigtableInstanceId();

        void setBigtableInstanceId(String bigtableInstanceId);

        @Description("The Bigtable table ID in the instance.")
        @Default.String("student_class_school_master")
        String getBigtableTableId();

        void setBigtableTableId(String bigtableTableId);
    }

    public static CloudBigtableTableConfiguration batchWriteFlowControlExample(BigtableOptions options) {
        CloudBigtableTableConfiguration bigtableTableConfig = new CloudBigtableTableConfiguration.Builder()
                .withProjectId(options.getBigtableProjectId())
                .withInstanceId(options.getBigtableInstanceId())
                .withTableId(options.getBigtableTableId())
                .withConfiguration(BigtableOptionsFactory.BIGTABLE_ENABLE_BULK_MUTATION_FLOW_CONTROL,
                        "true")
                .build();
        return bigtableTableConfig;
    }
}
