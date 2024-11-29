package com.example.etl.pipeline.sideinput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

import com.example.etl.entity.TeacherEntity;
import com.example.etl.pipeline.MainProcessPipeline.BigtableOptions;
import com.example.etl.util.BigQueryDelegate;
import com.google.cloud.bigquery.TableResult;

public class StudentTeacherRelationPipeline
        extends PTransform<PBegin, PCollectionView<Map<String, List<TeacherEntity>>>> {

    @Override
    public PCollectionView<Map<String, List<TeacherEntity>>> expand(PBegin input) {
        BigtableOptions options = input.getPipeline().getOptions().as(BigtableOptions.class);
        PCollection<Long> pCollection;

        pCollection = input.apply(GenerateSequence.from(0).withRate(1, Duration.standardMinutes(1L)))
                .apply(Window.<Long>into(new GlobalWindows())
                        .triggering(Repeatedly.forever(AfterProcessingTime.pastFirstElementInPane()))
                        .discardingFiredPanes());

        PCollection<Map<String, List<TeacherEntity>>> mapCollection;
        mapCollection = pCollection.apply(ParDo.of(new Logic(options.getProjectId())));

        return mapCollection.apply(View.asSingleton());
    }

    private static class Logic extends DoFn<Long, Map<String, List<TeacherEntity>>> {
        private String projectId;

        public Logic(String projectId) {
            this.projectId = projectId;
        }

        @ProcessElement
        public void processElement(ProcessContext c) {
            Map<String, List<TeacherEntity>> map = new HashMap<>();
            BigQueryDelegate delegate = new BigQueryDelegate();
            delegate.getInstance(projectId);
            try {
                String query = "SELECT student_id, teacher_id, teacher_name, subject FROM my_dataset.student_teacher_relation";
                TableResult tableResult = delegate.query(query);
                tableResult.getValues()
                .forEach((val) -> {
                if(Objects.nonNull(map.get(val.get("student_id").getStringValue()))){
                    TeacherEntity entity = new TeacherEntity();
                    entity.setStudentId(val.get("student_id").getStringValue());
                    entity.setTeacherId(val.get("teacher_id").getStringValue());
                    entity.setTeacherName(val.get("teacher_name").getStringValue());
                    entity.setSubject(val.get("subject").getStringValue());
                    map.get(val.get("student_id").getStringValue()).add(entity);
                } else {
                    TeacherEntity entity = new TeacherEntity();
                    entity.setStudentId(val.get("student_id").getStringValue());
                    entity.setTeacherId(val.get("teacher_id").getStringValue());
                    entity.setTeacherName(val.get("teacher_name").getStringValue());
                    entity.setSubject(val.get("subject").getStringValue());
                    List<TeacherEntity> list = new ArrayList<>();
                    list.add(entity);
                    map.put(val.get("student_id").getStringValue(), list);
                }
            });

            if (tableResult.hasNextPage()) {
                tableResult.getValues().forEach(
                    (val) -> {
                        if(Objects.nonNull(map.get(val.get("student_id").getStringValue()))){
                            TeacherEntity entity = new TeacherEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setTeacherId(val.get("teacher_id").getStringValue());
                            entity.setTeacherName(val.get("teacher_name").getStringValue());
                            entity.setSubject(val.get("subject").getStringValue());
                            map.get(val.get("student_id").getStringValue()).add(entity);
                        } else {
                            TeacherEntity entity = new TeacherEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setTeacherId(val.get("teacher_id").getStringValue());
                            entity.setTeacherName(val.get("teacher_name").getStringValue());
                            entity.setSubject(val.get("subject").getStringValue());
                            List<TeacherEntity> list = new ArrayList<>();
                            list.add(entity);
                            map.put(val.get("student_id").getStringValue(), list);
                        }
                    }
                );
            }

            if (tableResult.hasNextPage()) {
                tableResult.getNextPage().iterateAll().forEach(
                    (val) -> {
                        if(Objects.nonNull(map.get(val.get("student_id").getStringValue()))){
                            TeacherEntity entity = new TeacherEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setTeacherId(val.get("teacher_id").getStringValue());
                            entity.setTeacherName(val.get("teacher_name").getStringValue());
                            entity.setSubject(val.get("subject").getStringValue());
                            map.get(val.get("student_id").getStringValue()).add(entity);
                        } else {
                            TeacherEntity entity = new TeacherEntity();
                            entity.setStudentId(val.get("student_id").getStringValue());
                            entity.setTeacherId(val.get("teacher_id").getStringValue());
                            entity.setTeacherName(val.get("teacher_name").getStringValue());
                            entity.setSubject(val.get("subject").getStringValue());
                            List<TeacherEntity> list = new ArrayList<>();
                            list.add(entity);
                            map.put(val.get("student_id").getStringValue(), list);
                        }
                    }
                );
            }
            c.output(map);
            } catch (Exception e) {
                // donothing;
            }

        }
    }

}
