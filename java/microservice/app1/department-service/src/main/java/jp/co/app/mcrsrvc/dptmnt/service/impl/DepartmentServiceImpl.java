package jp.co.app.mcrsrvc.dptmnt.service.impl;

import org.springframework.stereotype.Service;

import jp.co.app.mcrsrvc.dptmnt.entity.Department;
import jp.co.app.mcrsrvc.dptmnt.repository.DepartmentRepository;
import jp.co.app.mcrsrvc.dptmnt.service.DepartmentService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

}
