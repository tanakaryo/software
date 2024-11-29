package com.example.etl.pipeline;

import java.util.Arrays;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.StreamingOptions;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class AppPipeline {
    public interface Options extends StreamingOptions {
        @Description("Input text to print.")
        @Default.String("My input text")
        String getInputText();

        void setInputText(String value);

    }

    public static PCollection<String> buildPipeline(Pipeline pipeline, String inputText) {
        return pipeline.apply("Create elements", Create.of(Arrays.asList("Hello", "World!", inputText)))
                .apply("Print elements", MapElements.into(TypeDescriptors.strings()).via(x -> {
                    System.out.println(x);
                    return x;
                }));
    }

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline pipeline = Pipeline.create(options);
        AppPipeline.buildPipeline(pipeline, options.getInputText());
        pipeline.run().waitUntilFinish();
    }
}
