package com.example.etl.beam;

import java.util.Arrays;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class WordExtractPipeline {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        PCollection<String> reviews = p.apply(Create.of(Arrays.asList("Thank you Neighbor", "Works well for the price")));
        PCollection<String> words = reviews.apply(
            FlatMapElements.into(TypeDescriptors.strings())
            .via((review) -> Arrays.asList(review.split(" ")))
        );

        words.apply(ParDo.of(new DoFn<String,Void>() {
            @ProcessElement
            public void processElement(@Element String value) {
                System.out.println(value);
            }
        }));

        p.run().waitUntilFinish();
    }
}
