package com.example.etl.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.spanner.SpannerIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.transforms.ToString;
import org.apache.beam.sdk.values.PCollection;

import com.example.etl.EstimateSize;
import com.google.cloud.spanner.Struct;


public class SpannerReadPipeline {
    public interface Options extends PipelineOptions {

        @Description("Spanner instance ID to query from")
        @Validation.Required
        @Default.String("test-instance")
        String getInstanceId();
    
        void setInstanceId(String value);
    
        @Description("Spanner database name to query from")
        @Validation.Required
        @Default.String("example-db")
        String getDatabaseId();
    
        void setDatabaseId(String value);
    
        @Description("Spanner table name to query from")
        @Validation.Required
        @Default.String("Singers")
        String getTable();
    
        void setTable(String value);
    
        @Description("Output filename for records size")
        @Validation.Required
        @Default.String("out.txt")
        String getOutput();
    
        void setOutput(String value);
      }

    public static void main(String[] args) {
    Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
    Pipeline pipeline = Pipeline.create(options);

    String instanceId = options.getInstanceId();
    String databaseId = options.getDatabaseId();
    // [START spanner_dataflow_read]
    // Query for all the columns and rows in the specified Spanner table
    PCollection<Struct> records = pipeline.apply(
        SpannerIO.read()
            .withInstanceId(instanceId)
            .withDatabaseId(databaseId)
            .withQuery("SELECT * FROM " + options.getTable()));
    // [END spanner_dataflow_read]


    PCollection<Long> tableEstimatedSize = records
        // Estimate the size of every row
        .apply(EstimateSize.create())
        // Sum all the row sizes to get the total estimated size of the table
        .apply(Sum.longsGlobally());

    // Write the total size to a file
    tableEstimatedSize
        .apply(ToString.elements())
        .apply(TextIO.write().to(options.getOutput()).withoutSharding());

    pipeline.run().waitUntilFinish();
  }
}
