#!/bin/bash

## update gcloud
gcloud components update beta

## install cbt
gcloud components update
gcloud components install cbt

## bigtable emulator start
gcloud beta emulators bigtable start
gcloud beta emulators bigtable start --host-port=localhost:8086

## bigtable emulators env-init
$(gcloud beta emulators bigtable env-init)

## cbt createtable
cbt createtable student_class_school_master

## cbt create columnfamily
cbt createfamily student_class_school_master student
cbt createfamily student_class_school_master class
cbt createfamily student_class_school_master school

## set testdata
cbt set student_class_school_master 123456#elementary student:student_id=123456 student:student_name=yoshio-kubota class:class_grade=5 class:class_number=1 school:school_type=elementary 

mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.AppPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.PartitionPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.BigtableReadPipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.BigtableWritePipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.PubSubBigtableWritePipeline
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.PubSubBigtableWritePipeline2
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.MainProcessPipeline

423456#elementary
523456#elementary

mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.BigQueryReadPipeline -Dexec.args="--tempLocation=gs://test-beam-bigquery-20241114-jp"

mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.SpannerReadPipeline2
mvn compile exec:java -Dexec.mainClass=com.example.etl.pipeline.SpannerReadPipeline3
