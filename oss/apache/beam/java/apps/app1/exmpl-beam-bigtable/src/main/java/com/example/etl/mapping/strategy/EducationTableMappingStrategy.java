package com.example.etl.mapping.strategy;

import com.example.etl.dto.StudentDto;
import com.example.etl.entity.EducationTableEntity;
import com.example.etl.entity.TableEntity;
import com.example.etl.mapping.MappingStrategy;

public class EducationTableMappingStrategy implements MappingStrategy {

    private StudentDto dto;

    public EducationTableMappingStrategy(StudentDto dto) {
        this.dto = dto;
    }

    @Override
    public TableEntity translate() {

        return new EducationTableEntity();
    }

}
