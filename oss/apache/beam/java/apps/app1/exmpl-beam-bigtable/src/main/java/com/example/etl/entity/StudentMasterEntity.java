package com.example.etl.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMasterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String studentName;
    private String classGrade;
    private String classNumber;
    private String schoolType;


}
