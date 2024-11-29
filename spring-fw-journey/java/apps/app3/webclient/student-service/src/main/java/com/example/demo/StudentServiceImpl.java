package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private WebClient webClient;

    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public ResponseDto getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDto studentDto = toStudentDto(student);

        SchoolClassDto schoolClassDto = webClient.get()
        .uri("http://localhost:8081/api/v1/school-classes/" + student.getClassId())
        .retrieve().bodyToMono(SchoolClassDto.class)
        .block();

        return new ResponseDto(schoolClassDto, studentDto);
    }

    private StudentDto toStudentDto(Student student) {
        return new StudentDto(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getClassId(),
            student.getStudentGrade()
        );
    }
}

