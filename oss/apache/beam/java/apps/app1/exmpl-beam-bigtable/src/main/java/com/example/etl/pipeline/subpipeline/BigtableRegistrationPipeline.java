package com.example.etl.pipeline.subpipeline;

import java.util.List;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.example.etl.dto.StudentDto;
import com.example.etl.entity.TeacherEntity;
import com.google.cloud.bigtable.beam.CloudBigtableIO;
import com.google.cloud.bigtable.beam.CloudBigtableTableConfiguration;

public class BigtableRegistrationPipeline extends PTransform<PCollection<StudentDto>, PDone> {

    @Override
    public PDone expand(PCollection<StudentDto> input) {

        return input.apply(ParDo.of(new DoFn<StudentDto, StudentDto>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
                StudentDto dto = c.element();
                StudentDto outDto = new StudentDto();
                // MappingStrategy strategy =
                // BigtableMappingStrategyFactory.getMappingStrategy("SAMPLE", dto);
                // TableEntity entity = strategy.translate();
                BeanUtils.copyProperties(outDto, dto);
                c.output(outDto);
            }
        })).apply(ParDo.of(new DoFn<StudentDto, Mutation>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
                StudentDto dto = c.element();

                List<TeacherEntity> list = dto.getTeacherEntityList();
                for (TeacherEntity teacher : list) {
                    String rowKey = dto.getStudentId() + "#" + teacher.getTeacherId();

                    long timestamp = System.currentTimeMillis();
                    Put row = new Put(Bytes.toBytes(rowKey));
    
                    row.addColumn(
                            Bytes.toBytes("student"),
                            Bytes.toBytes("student_id"),
                            timestamp,
                            Bytes.toBytes(dto.getStudentId()))
                            .addColumn(
                                    Bytes.toBytes("student"),
                                    Bytes.toBytes("student_name"),
                                    timestamp,
                                    Bytes.toBytes(dto.getStudentName()))
                            .addColumn(
                                    Bytes.toBytes("class"),
                                    Bytes.toBytes("class_grade"),
                                    timestamp,
                                    Bytes.toBytes(dto.getClassGrade()))
                            .addColumn(Bytes.toBytes("class"),
                                    Bytes.toBytes("class_number"),
                                    timestamp,
                                    Bytes.toBytes(dto.getClassNumber()))
                            .addColumn(
                                    Bytes.toBytes("school"), 
                                    Bytes.toBytes("school_type"),
                                    timestamp,
                                    Bytes.toBytes(dto.getSchoolType()))
                            .addColumn(Bytes.toBytes("school"), 
                            Bytes.toBytes("school_subject"),
                            timestamp,
                            Bytes.toBytes(teacher.getSubject()));
    
                    c.output(row);
                }
            }
        })).apply(CloudBigtableIO.writeToTable(new CloudBigtableTableConfiguration.Builder()
                .withProjectId("fake")
                .withInstanceId("fake")
                .withTableId("student_class_school_master")
                .build()));
    }

}
