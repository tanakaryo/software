package com.example.etl.pipeline.subpipeline;

import java.util.List;
import java.util.Map;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.sdk.values.PDone;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;
import org.apache.commons.beanutils.BeanUtils;

import com.example.etl.dto.StudentDto;
import com.example.etl.entity.StudentMasterEntity;
import com.example.etl.entity.TeacherEntity;
import com.example.etl.pipeline.sideinput.StudentMasterPipeline;
import com.example.etl.pipeline.sideinput.StudentTeacherRelationPipeline;

public class SubStreamPipeline extends PTransform<PCollection<StudentDto>, PDone> {

    TupleTag<StudentDto> spannerRegistrationFlow = new TupleTag<StudentDto>() {};
    TupleTag<StudentDto> bigtableRegistrationFlow = new TupleTag<StudentDto>() {};

    @Override
    public PDone expand(PCollection<StudentDto> input) {

        PCollectionView<Map<String, StudentMasterEntity>> studentMasterViewMap = input.getPipeline().apply(new StudentMasterPipeline());
        PCollectionView<Map<String, List<TeacherEntity>>> teacherRelationViewMap = input.getPipeline().apply(new StudentTeacherRelationPipeline());

        
        PCollectionTuple pCollection = input.apply(ParDo.of(new DoFn<StudentDto,StudentDto>() {
           @ProcessElement
           public void processElement(ProcessContext ctxt) throws Exception {
            StudentDto dto = ctxt.element();
            System.out.println(dto.getStudentName());

            Map<String, StudentMasterEntity> studentMasterMap = ctxt.sideInput(studentMasterViewMap);

            StudentMasterEntity entity = studentMasterMap.get(dto.getStudentId());

            StudentDto outDto = new StudentDto();
            BeanUtils.copyProperties(outDto, dto);
            outDto.setStudentEntity(entity);
            System.out.println(entity);


            ctxt.output(outDto);
           };
        }).withSideInputs(studentMasterViewMap)
        ).apply(ParDo.of(new DoFn<StudentDto,StudentDto>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
                StudentDto dto = c.element();

                Map<String, List<TeacherEntity>> relationMap = c.sideInput(teacherRelationViewMap);

                List<TeacherEntity> relation = relationMap.get(dto.getStudentId());
                StudentDto outDto = new StudentDto();
                BeanUtils.copyProperties(outDto, dto);
                outDto.setTeacherEntityList(relation);
                System.out.println(outDto);
                c.output(spannerRegistrationFlow, outDto);
                c.output(bigtableRegistrationFlow, outDto);
            }
        }).withSideInputs(teacherRelationViewMap)
        .withOutputTags(spannerRegistrationFlow, TupleTagList.of(bigtableRegistrationFlow))
        );

        pCollection.get(spannerRegistrationFlow).apply(new SpannerRegistrationPipeline());
        pCollection.get(bigtableRegistrationFlow).apply(new BigtableRegistrationPipeline());
        
        return PDone.in(pCollection.getPipeline());
    }

}
