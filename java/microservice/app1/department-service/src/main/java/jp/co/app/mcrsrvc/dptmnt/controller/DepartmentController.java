package jp.co.app.mcrsrvc.dptmnt.controller;

import org.springframework.web.bind.annotation.RestController;

import jp.co.app.mcrsrvc.dptmnt.entity.Department;
import jp.co.app.mcrsrvc.dptmnt.service.DepartmentService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department result = departmentService.saveDepartment(department);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) {
        Department result = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(result);
    }


}
