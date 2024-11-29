package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/school-classes")
@AllArgsConstructor
public class SchoolController {

    private SchoolClassService schoolClassService;

    @PostMapping
    public ResponseEntity<SchoolClass> saveSchoolClass(@RequestBody SchoolClass schoolClass) {
        SchoolClass result = schoolClassService.saveSchoolClass(schoolClass);
        return new ResponseEntity<SchoolClass>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<SchoolClass> getSchoolClassById(@PathVariable("id") Long classId) {
        SchoolClass result = schoolClassService.getSchoolClass(classId);
        return ResponseEntity.ok(result);
    }
}
