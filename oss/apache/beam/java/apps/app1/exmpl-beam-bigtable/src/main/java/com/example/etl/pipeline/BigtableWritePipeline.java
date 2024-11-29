package com.example.etl.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.cloud.bigtable.beam.CloudBigtableIO;
import com.google.cloud.bigtable.beam.CloudBigtableTableConfiguration;
import com.google.cloud.bigtable.hbase.BigtableOptionsFactory;

public class BigtableWritePipeline {

    public static void main(String[] args) {
        BigtableOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(BigtableOptions.class);
        Pipeline p = Pipeline.create(options);
        CloudBigtableTableConfiguration bigtableTableConfig = new CloudBigtableTableConfiguration.Builder()
                .withProjectId(options.getBigtableProjectId())
                .withInstanceId(options.getBigtableInstanceId())
                .withTableId(options.getBigtableTableId())
                .build();

        p.apply(Create.of("9987654#senior", "9877456#elementary"))
                .apply(
                        ParDo.of(
                                new DoFn<String, Mutation>() {
                                    @ProcessElement
                                    public void processElement(@Element String rowkey, OutputReceiver<Mutation> out) {
                                        long timestamp = System.currentTimeMillis();
                                        Put row = new Put(Bytes.toBytes(rowkey));

                                        row.addColumn(
                                                Bytes.toBytes("student"),
                                                Bytes.toBytes("student_id"),
                                                timestamp,
                                                Bytes.toBytes("223456"));
                                        out.output(row);
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
