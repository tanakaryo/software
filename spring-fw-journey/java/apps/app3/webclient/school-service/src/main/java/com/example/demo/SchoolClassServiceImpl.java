package com.example.demo;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private SchoolClassRepository schoolClassRepository;

    @Override
    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public SchoolClass getSchoolClass(Long classId) {
        return schoolClassRepository.findById(classId).get();
    }
}
