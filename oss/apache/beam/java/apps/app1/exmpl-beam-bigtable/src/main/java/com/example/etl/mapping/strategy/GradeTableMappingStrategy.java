package com.example.etl.mapping.strategy;

import com.example.etl.dto.StudentDto;
import com.example.etl.entity.GradeTableEntity;
import com.example.etl.entity.TableEntity;
import com.example.etl.mapping.MappingStrategy;

public class GradeTableMappingStrategy implements MappingStrategy {

    private StudentDto dto;

    public GradeTableMappingStrategy(StudentDto dto) {
        this.dto  = dto;
    }

    @Override
    public TableEntity translate() {
        return new GradeTableEntity();
    }

}
