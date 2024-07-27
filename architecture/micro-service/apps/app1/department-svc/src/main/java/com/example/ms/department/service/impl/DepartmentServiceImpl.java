package com.example.ms.department.service.impl;

import org.springframework.stereotype.Service;

import com.example.ms.department.entity.Department;
import com.example.ms.department.repository.DepartmentRepository;
import com.example.ms.department.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return this.departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return this.departmentRepository.findById(departmentId).get();
    }

}
