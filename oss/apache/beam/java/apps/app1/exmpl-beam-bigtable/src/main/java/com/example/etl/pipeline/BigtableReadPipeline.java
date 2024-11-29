package com.example.etl.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.Read;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.cloud.bigtable.beam.CloudBigtableIO;
import com.google.cloud.bigtable.beam.CloudBigtableScanConfiguration;

public class BigtableReadPipeline {
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

  public static void main(String[] args) {
    BigtableOptions options =
        PipelineOptionsFactory.fromArgs(args).withValidation().as(BigtableOptions.class);
    Pipeline p = Pipeline.create(options);

    Scan scan = new Scan();
    scan.setCacheBlocks(false);
    scan.setFilter(new FirstKeyOnlyFilter());

    CloudBigtableScanConfiguration config =
        new CloudBigtableScanConfiguration.Builder()
            .withProjectId(options.getBigtableProjectId())
            .withInstanceId(options.getBigtableInstanceId())
            .withTableId(options.getBigtableTableId())
            .withScan(scan)
            .build();

    p.apply(Read.from(CloudBigtableIO.read(config)))
        .apply(
            ParDo.of(
                new DoFn<Result, Void>() {
                  @ProcessElement
                  public void processElement(@Element Result row, OutputReceiver<Void> out) {
                    System.out.println(Bytes.toString(row.getRow()));
                  }
                }));

    p.run().waitUntilFinish();
  }
}
