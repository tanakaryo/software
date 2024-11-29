package com.example.etl.mapping;

import com.example.etl.dto.StudentDto;
import com.example.etl.mapping.strategy.CourseTableMappingStrategy;
import com.example.etl.mapping.strategy.EducationTableMappingStrategy;

public class SpannerMappingStrategyFactory {

    public static MappingStrategy getMappingStrategy(String tableName , StudentDto dto) {


        if ("COURSE".equals(tableName)) {

            if ("junior".equals(dto.getSchoolType())) {
                return new CourseTableMappingStrategy(dto);
            }
        }

        if ("EDUCATION".equals(tableName)) {
            if ("junior".equals(dto.getSchoolType())) {
                return new EducationTableMappingStrategy(dto);
            }
        }

        if ("GRADE".equals(tableName)) {
            if ("junior".equals(dto.getSchoolType())) {
                return new EducationTableMappingStrategy(dto);
            }
        }

        return null;
    }
}
