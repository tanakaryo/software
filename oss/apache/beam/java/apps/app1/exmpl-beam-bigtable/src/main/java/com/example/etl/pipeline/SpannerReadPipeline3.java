package com.example.etl.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.spanner.SpannerConfig;
import org.apache.beam.sdk.io.gcp.spanner.SpannerIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Default.Enum;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider.StaticValueProvider;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.values.PCollectionView;

import com.google.cloud.spanner.Dialect;
import com.google.cloud.spanner.Mutation;

public class SpannerReadPipeline3 {
    public interface SpannerOptions extends DataflowPipelineOptions {

      @Description("Spanner emulator endpoint")
      @Default.String("http://localhost:9010")
      String getEndpoint();
      void setEndpoint(String value);

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
      }

  public static void main(String[] args) {
    SpannerOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(SpannerOptions.class);
    Pipeline pipeline = Pipeline.create(options);

    String instanceId = options.getInstanceId();
    String databaseId = options.getDatabaseId();
    Dialect dialect = options.getDialect();

    SpannerConfig config = SpannerConfig.create();
    config = config.withEmulatorHost(StaticValueProvider.of(options.getEndpoint()));

    PCollectionView<Dialect> dialectView = pipeline.apply(Create.of(Dialect.GOOGLE_STANDARD_SQL)).apply(View.asSingleton());

    pipeline.apply(Create.of("elems"))
    .apply("CreateSingersMutation", ParDo.of(new DoFn<String,Mutation>() {
      @ProcessElement
      public void processElement(ProcessContext c) {
        c.output(
          Mutation.newInsertOrUpdateBuilder("Singers")
          .set("singerId").to("7")
          .set("firstName").to("Tohmas")
          .set("lastName").to("Edison")
          .build());
      }
    }))
    // .apply("Write Singers", SpannerIO.write()
    // .withInstanceId(instanceId)
    // .withDatabaseId(databaseId)
    // .withDialectView(dialectView)
    // .withEmulatorHost("http://localhost:9010"));
    .apply(SpannerIO.write().withSpannerConfig(    config.withProjectId("aspf-jp-test")
    .withInstanceId("test-instance")
    .withDatabaseId("example-db")));

    pipeline.run().waitUntilFinish();
  }
}
