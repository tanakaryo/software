package com.example.etl.pipeline.sideinput;

import java.util.HashMap;
import java.util.Map;

import org.apache.beam.sdk.io.GenerateSequence;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.transforms.windowing.AfterProcessingTime;
import org.apache.beam.sdk.transforms.windowing.GlobalWindows;
import org.apache.beam.sdk.transforms.windowing.Repeatedly;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;
import org.joda.time.Duration;

import com.example.etl.entity.StudentMasterEntity;
import com.example.etl.pipeline.MainProcessPipeline.BigtableOptions;
import com.example.etl.util.BigQueryDelegate;
import com.google.cloud.bigquery.TableResult;

public class StudentMasterPipeline extends PTransform<PBegin, PCollectionView<Map<String, StudentMasterEntity>>> {

    @Override
    public PCollectionView<Map<String, StudentMasterEntity>> expand(PBegin input) {
        BigtableOptions options = input.getPipeline().getOptions().as(BigtableOptions.class);
        PCollection<Long> pCollection;

        pCollection = input.apply(GenerateSequence.from(0).withRate(1, Duration.standardMinutes(1L)))
        .apply(Window.<Long>into(new GlobalWindows()).triggering(Repeatedly.forever(AfterProcessingTime.pastFirstElementInPane()))
        .discardingFiredPanes());

        PCollection<Map<String, StudentMasterEntity>> mapCollection;
        mapCollection = pCollection.apply(ParDo.of(new Logic(options.getProjectId())));

        return mapCollection.apply(View.asSingleton());
    }

    private static class Logic extends DoFn<Long, Map<String, StudentMasterEntity>> {
        private String projectId;

        public Logic(String projectId) {
            this.projectId = projectId;
        }

        @ProcessElement
        public void processElement(ProcessContext c) {
            Map<String, StudentMasterEntity> map = new HashMap<>();
            BigQueryDelegate delegate = new BigQueryDelegate();
            delegate.getInstance(projectId);
            try {
                String query = "SELECT student_id, student_name, class_grade, class_number, school_type FROM my_dataset.student_master";
                TableResult tableResult = delegate.query(query);

                tableResult.getValues().forEach(
                    (val) -> {
                        StudentMasterEntity entity = new StudentMasterEntity();
                        entity.setStudentId(val.get("student_id").getStringValue());
                        entity.setStudentName(val.get("student_name").getStringValue());
                        entity.setClassGrade(val.get("class_grade").getStringValue());
                        entity.setClassNumber(val.get("class_number").getStringValue());
                        entity.setSchoolType(val.get("school_type").getStringValue());
                        map.put(val.get("student_id").getStringValue(), entity);
                    });
                
                if (tableResult.hasNextPage()) {
                    tableResult.getValues().forEach(
                        (val) -> {
                            StudentMasterEntity entity = new StudentMasterEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setStudentName(val.get("student_name").getStringValue());
                            entity.setClassGrade(val.get("class_grade").getStringValue());
                            entity.setClassNumber(val.get("class_number").getStringValue());
                            entity.setSchoolType(val.get("school_type").getStringValue());
                            map.put(val.get("student_id").getStringValue(), entity);
                        }
                    );
                }

                if (tableResult.hasNextPage()) {
                    tableResult.getNextPage().iterateAll().forEach(
                        (val) -> {
                            StudentMasterEntity entity = new StudentMasterEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setStudentName(val.get("student_name").getStringValue());
                            entity.setClassGrade(val.get("class_grade").getStringValue());
                            entity.setClassNumber(val.get("class_number").getStringValue());
                            entity.setSchoolType(val.get("school_type").getStringValue());
                            map.put(val.get("student_id").getStringValue(), entity);
                        }
                    );
                }

                c.output(map);
            } catch(Exception e) {
                return;
            }
        }
    }

}
