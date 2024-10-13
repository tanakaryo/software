package com.example.etl.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Mean;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class ComputeAverageRatingPipeline {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        PCollection<Double> productRatings = p.apply(Create.of(4.5, 3.8, 5.0,4.2, 3.6 ));
        PCollection<Double> avgRatings = productRatings.apply("Compute Average Ratings", Mean.globally());
        avgRatings.apply(ParDo.of(new PrintFn()));

        p.run().waitUntilFinish();
    }

    static class PrintFn extends DoFn<Double, Void> {
        @ProcessElement
        public void processElement(@Element Double avgRating ) {
            System.out.println("Avg Rating is : " + avgRating);
        }
    }
}
