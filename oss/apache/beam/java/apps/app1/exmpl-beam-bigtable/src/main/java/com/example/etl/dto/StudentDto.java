package com.example.etl.dto;

import java.io.Serializable;
import java.util.List;

import com.example.etl.entity.StudentMasterEntity;
import com.example.etl.entity.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class StudentDto implements Serializable {

    private static final long serialVersionUID = -2170800526658571029L;

    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("class_grade")
    private String classGrade;
    @JsonProperty("class_number")
    private String classNumber;

    @JsonProperty("school_type")
    private String schoolType;

    @JsonProperty("student_entity")
    private StudentMasterEntity studentEntity;

    @JsonProperty("teacher_entity_list")
    private List<TeacherEntity> teacherEntityList;
}
