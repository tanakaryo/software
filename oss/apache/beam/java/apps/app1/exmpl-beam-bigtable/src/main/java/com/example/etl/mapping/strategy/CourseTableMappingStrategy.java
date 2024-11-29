package com.example.etl.mapping.strategy;

import com.example.etl.dto.StudentDto;
import com.example.etl.entity.CourseTableEntity;
import com.example.etl.entity.TableEntity;
import com.example.etl.mapping.MappingStrategy;

public class CourseTableMappingStrategy implements MappingStrategy {

    private StudentDto dto;

    public CourseTableMappingStrategy(StudentDto dto) {
        this.dto = dto;
    }

    @Override
    public TableEntity translate() {

        return (TableEntity)new CourseTableEntity();
    }

}
