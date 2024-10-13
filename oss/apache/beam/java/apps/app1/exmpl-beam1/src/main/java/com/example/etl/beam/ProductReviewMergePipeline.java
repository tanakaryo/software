package com.example.etl.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;

public class ProductReviewMergePipeline {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        PCollection<String> review1 = p.apply(Create.of("Excellent", "Very good"));
        PCollection<String> review2 = p.apply(Create.of("Average", "Good"));

        PCollection<String> mergedReviews = PCollectionList.of(review1).and(review2)
        .apply(Flatten.pCollections());

        mergedReviews.apply(ParDo.of(new DoFn<String,Void>() {
            @ProcessElement
            public void processElement(@Element String review) {
                System.out.println(review);
            }
         }));

         p.run().waitUntilFinish();
    }
}
