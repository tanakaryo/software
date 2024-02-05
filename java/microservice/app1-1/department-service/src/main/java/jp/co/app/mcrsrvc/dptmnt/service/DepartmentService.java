package jp.co.app.mcrsrvc.dptmnt.service;

import jp.co.app.mcrsrvc.dptmnt.entity.Department;

public interface DepartmentService {

    Department saveDepartment(Department department);
    
    Department getDepartmentById(Long departmentId);
}
