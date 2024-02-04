package jp.co.app.mcrsrvc.usr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private DepartmentDto departmentDto;
    private UserDto userDto;
}
