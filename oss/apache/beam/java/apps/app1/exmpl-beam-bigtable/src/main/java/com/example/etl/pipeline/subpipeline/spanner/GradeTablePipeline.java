package com.example.etl.pipeline.subpipeline.spanner;

import org.apache.beam.sdk.io.gcp.spanner.SpannerIO;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.sdk.values.PDone;
import org.apache.commons.beanutils.BeanUtils;

import com.example.etl.dto.StudentDto;
import com.google.cloud.spanner.Dialect;
import com.google.cloud.spanner.Mutation;

public class GradeTablePipeline extends PTransform<PCollection<StudentDto>, PDone> {

    @Override
    public PDone expand(PCollection<StudentDto> input) {

        PCollectionView<Dialect> dialectView = input.getPipeline().apply(Create.of(Dialect.GOOGLE_STANDARD_SQL)).apply(View.asSingleton());

        input.apply(ParDo.of(new DoFn<StudentDto, StudentDto>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
                StudentDto dto = c.element();

                StudentDto outDto = new StudentDto();

                // MappingStrategy strategy = SpannerMappingStrategyFactory.getMappingStrategy("COURSE", dto);
                // TableEntity entity = strategy.translate();

                BeanUtils.copyProperties(outDto, dto);

                c.output(outDto);
            }
        })).apply(ParDo.of(new DoFn<StudentDto, Mutation>() {
            @ProcessElement
            public void processElement(ProcessContext c) {

                StudentDto dto = c.element();

                c.output(
                    Mutation.newInsertOrUpdateBuilder("Singers")
                    .set("singerId").to(dto.getStudentId())
                    .set("firstName").to(dto.getStudentName())
                    .set("lastName").to(dto.getSchoolType())
                    .build()
                );
            }
        })).apply(SpannerIO.write()
        .withInstanceId("test-instance")
        .withDatabaseId("example-db")
        .withDialectView(dialectView)
        .withEmulatorHost("http://localhost:9010"));

        return PDone.in(input.getPipeline());
    }

}