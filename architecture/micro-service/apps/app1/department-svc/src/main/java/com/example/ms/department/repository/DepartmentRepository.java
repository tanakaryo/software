package com.example.ms.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
