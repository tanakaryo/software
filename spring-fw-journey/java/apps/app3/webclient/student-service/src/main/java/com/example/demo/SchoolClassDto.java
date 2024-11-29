package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassDto {

    private Long id;
    private String classGrade;
    private String classRank;
    private String classTeacher;
    private String classSubject;

}
