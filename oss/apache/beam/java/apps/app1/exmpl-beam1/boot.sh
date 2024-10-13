#!/bin/bash

mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.GcsTextReadPipeline -Dexec.args="--inputText=wowwowwow"
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.ProductReviewMergePipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.WordExtractPipeline

mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.FilterablePipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.GroupByKeyPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.CoGroupByKeyPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.ComputeAverageRatingPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.beam.PartitionPipeline