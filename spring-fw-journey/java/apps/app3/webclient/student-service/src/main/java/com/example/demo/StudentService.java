package com.example.demo;

public interface StudentService {

    Student saveStudent(Student student);

    ResponseDto getStudent(Long studentId);
    
}
