package com.example.etl.mapping;

import com.example.etl.entity.TableEntity;

public interface MappingStrategy {

    TableEntity translate();
}
