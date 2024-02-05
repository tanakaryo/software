package jp.co.app.mcrsrvc.dptmnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.app.mcrsrvc.dptmnt.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
