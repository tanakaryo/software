package com.example.etl.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.ParDo;

public class FilterablePipeline {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        p.apply(Create.of("This product is absolutely amazing.", "It's okay, but I've seen better.",
                "Fantastic quality and design.", "I didn't like this product at all."))
                .apply(Filter.by(review -> review.contains("amazing") || review.contains("Fantastic")))
                .apply(ParDo.of(new PrintReviewFn()));

        p.run().waitUntilFinish();
    }

    static class PrintReviewFn extends DoFn<String, Void> {
        @ProcessElement
        public void processElement(@Element String review) {
            System.out.println("Positive Review : " + review);
        }
    }
}
