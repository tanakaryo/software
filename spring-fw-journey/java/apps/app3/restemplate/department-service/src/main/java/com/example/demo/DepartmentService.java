package com.example.demo;

public interface DepartmentService {

    Department saveDepartment(Department department);
    
    Department getDepartmentById(Long departmentId);
}
