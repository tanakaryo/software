package com.example.ms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
