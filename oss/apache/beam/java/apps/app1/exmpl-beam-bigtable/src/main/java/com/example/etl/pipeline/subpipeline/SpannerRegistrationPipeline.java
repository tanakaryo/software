package com.example.etl.pipeline.subpipeline;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.PDone;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;
import org.apache.commons.beanutils.BeanUtils;

import com.example.etl.dto.StudentDto;
import com.example.etl.pipeline.subpipeline.spanner.CourseTablePipeline;
import com.example.etl.pipeline.subpipeline.spanner.EducationTablePipeline;
import com.example.etl.pipeline.subpipeline.spanner.GradeTablePipeline;

public class SpannerRegistrationPipeline extends PTransform<PCollection<StudentDto>, PDone> {

    TupleTag<StudentDto> gradeRegistrationFlow = new TupleTag<StudentDto>() {};
    TupleTag<StudentDto> courseRegistrationFlow = new TupleTag<StudentDto>() {};
    TupleTag<StudentDto> educationRegistrationFlow = new TupleTag<StudentDto>() {};

    @Override
    public PDone expand(PCollection<StudentDto> input) {

        PCollectionTuple pCollectionTuple = input.apply(ParDo.of(new DoFn<StudentDto, StudentDto>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
                StudentDto dto = c.element();

                StudentDto outDto = new StudentDto();
                BeanUtils.copyProperties(outDto, dto);

                c.output(gradeRegistrationFlow, outDto);
                c.output(courseRegistrationFlow, outDto);
                c.output(educationRegistrationFlow, outDto);
            }
        }).withOutputTags(gradeRegistrationFlow, TupleTagList.of(courseRegistrationFlow).and(educationRegistrationFlow)));

        pCollectionTuple.get(gradeRegistrationFlow).apply(new GradeTablePipeline());
        pCollectionTuple.get(courseRegistrationFlow).apply(new CourseTablePipeline());
        pCollectionTuple.get(educationRegistrationFlow).apply(new EducationTablePipeline());

        return PDone.in(pCollectionTuple.getPipeline());
    }




}
