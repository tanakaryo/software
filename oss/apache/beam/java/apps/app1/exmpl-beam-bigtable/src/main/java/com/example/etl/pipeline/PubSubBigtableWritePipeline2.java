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
import com.google.cloud.bigtable.beam.CloudBigtableIO;
import com.google.cloud.bigtable.beam.CloudBigtableTableConfiguration;
import com.google.cloud.bigtable.hbase.BigtableOptionsFactory;

public class PubSubBigtableWritePipeline2 {

    public static void main(String[] args) {
        BigtableOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(BigtableOptions.class);
        options.setStreaming(true);
        Pipeline p = Pipeline.create(options);
        CloudBigtableTableConfiguration bigtableTableConfig = new CloudBigtableTableConfiguration.Builder()
                .withProjectId(options.getBigtableProjectId())
                .withInstanceId(options.getBigtableInstanceId())
                .withTableId(options.getBigtableTableId())
                .build();

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
                // .apply(ParDo.of(new DoFn<String, Mutation>() {
                // @ProcessElement
                // public void processElement(@Element String rowkey, OutputReceiver<Mutation>
                // out) {
                // long timestamp = System.currentTimeMillis();
                // Put row = new Put(Bytes.toBytes(rowkey));

                // row.addColumn(
                // Bytes.toBytes("student"),
                // Bytes.toBytes("student_id"),
                // timestamp,
                // Bytes.toBytes("223456"));
                // row.addColumn(
                // Bytes.toBytes("student"),
                // Bytes.toBytes("student_name"),
                // timestamp,
                // Bytes.toBytes("takao-yamada"));
                // out.output(row);
                // }
                // }))
                .apply(ParDo.of(new DoFn<StudentDto, Mutation>() {
                    @ProcessElement
                    public void processElement(@Element StudentDto dto, OutputReceiver<Mutation> out) {

                        for (int i = 0; i < 10; i++) {
                            long timestamp = System.currentTimeMillis();
                            String rowKey = dto.getStudentId() + "#" + dto.getSchoolType() + "#" + String.valueOf(i);
                            Put row = new Put(Bytes.toBytes(rowKey));

                            // StudentId
                            row.addColumn(Bytes.toBytes("student"), Bytes.toBytes("student_id"), timestamp,
                                    Bytes.toBytes(dto.getStudentId()));
                            // StudentName
                            row.addColumn(Bytes.toBytes("student"), Bytes.toBytes("student_name"), timestamp,
                                    Bytes.toBytes(dto.getStudentName()));
                            // ClassGrade
                            row.addColumn(Bytes.toBytes("class"), Bytes.toBytes("class_grade"), timestamp,
                                    Bytes.toBytes(dto.getClassGrade()));
                            // ClassNumber
                            row.addColumn(Bytes.toBytes("class"), Bytes.toBytes("class_number"), timestamp,
                                    Bytes.toBytes(dto.getClassNumber()));
                            // SchoolType
                            row.addColumn(Bytes.toBytes("school"), Bytes.toBytes("school_type"), timestamp,
                                    Bytes.toBytes(dto.getSchoolType()));
                            out.output(row);
                        }

                    }
                }))
                .apply(CloudBigtableIO.writeToTable(bigtableTableConfig));

        p.run().waitUntilFinish();
    }

    public interface BigtableOptions extends DataflowPipelineOptions {

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
