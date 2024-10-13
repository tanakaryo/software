package com.example.etl.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

public class GroupByKeyPipeline {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        PCollection<KV<String, String>> makers = p.apply(Create.of(KV.of("Electric Guitar Makers", "Fender"),
        KV.of("Electric Guitar Makers", "Gibson"),
        KV.of("Saxophone Makers", "Selmer"),
        KV.of("Saxophone Makers", "Buffet Crampon")));

        PCollection<KV<String, Iterable<String>>> aggregatedMakers =  makers.apply(GroupByKey.create());

        aggregatedMakers.apply(ParDo.of(new DoFn< KV<String, Iterable<String>>, Void>() {
            @ProcessElement
            public void processElement(@Element KV<String, Iterable<String>> kv ) {
                System.out.println(kv.getKey() + " : " + kv.getValue());
            }
        }));

        p.run().waitUntilFinish();
    }
}
