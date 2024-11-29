package com.example.etl.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.spanner.SpannerIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Default.Enum;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.transforms.ToString;
import org.apache.beam.sdk.values.PCollection;

import com.example.etl.EstimateSize;
import com.google.cloud.spanner.Dialect;
import com.google.cloud.spanner.Struct;

public class SpannerReadPipeline2 {
    public interface SpannerOptions extends DataflowPipelineOptions {
        @Description("Spanner instance ID to query from")
        @Default.String("test-instance")
        String getInstanceId();

        void setInstanceId(String instanceId);
    
        @Description("Spanner Database name to query from")
        @Default.String("example-db")
        String getDatabaseId();
    
        void setDatabaseId(String databaseId);
    
        @Description("Dialect of the database that is used")
        @Default
        @Enum("GOOGLE_STANDARD_SQL")
        Dialect getDialect();

        void setDialect(Dialect dialect);

        @Description("Output filename for records size")
        @Default.String("out.txt")
        String getOutput();
    
        void setOutput(String value);
      }

  public static void main(String[] args) {
    SpannerOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(SpannerOptions.class);
    Pipeline pipeline = Pipeline.create(options);

    String instanceId = options.getInstanceId();
    String databaseId = options.getDatabaseId();
    Dialect dialect = options.getDialect();
    PCollection<Struct> records;

    if (dialect == Dialect.POSTGRESQL) {
      records = postgresSqlRead(instanceId, databaseId, pipeline);
    } else {
      records = googleSqlRead(instanceId, databaseId, pipeline);
    }

    PCollection<Long> tableEstimatedSize = records.apply(EstimateSize.create()).apply(Sum.longsGlobally());

    tableEstimatedSize
    .apply(ToString.elements())
    .apply(TextIO.write().to(options.getOutput()).withoutSharding());

  }
  static PCollection<Struct> googleSqlRead(
    String instanceId, String databaseId, Pipeline pipeline) {
  PCollection<Struct> records = pipeline.apply(
      SpannerIO.read()
          .withInstanceId(instanceId)
          .withDatabaseId(databaseId)
          .withTable("Singers")
          .withColumns("SingerId", "FirstName", "LastName"));
  return records;
}

  static PCollection<Struct> postgresSqlRead(
    String instanceId, String databaseId, Pipeline pipeline
  ) {
    PCollection<Struct> records = pipeline.apply(
      SpannerIO.read()
      .withInstanceId(instanceId)
      .withDatabaseId(databaseId)
      .withTable("singers")
      .withColumns("singer_id", "first_name","last_name")
    );
    return records;
  }
}
