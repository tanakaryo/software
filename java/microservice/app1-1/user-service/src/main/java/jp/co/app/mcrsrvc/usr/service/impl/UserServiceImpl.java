package jp.co.app.mcrsrvc.usr.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import jp.co.app.mcrsrvc.usr.dto.DepartmentDto;
import jp.co.app.mcrsrvc.usr.dto.ResponseDto;
import jp.co.app.mcrsrvc.usr.dto.UserDto;
import jp.co.app.mcrsrvc.usr.entity.User;
import jp.co.app.mcrsrvc.usr.repository.UserRepository;
import jp.co.app.mcrsrvc.usr.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = this.mapToUser(user);

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
        //     "http://localhost:8081/api/departments/" + user.getDepartmentId()
        //     , DepartmentDto.class);

        DepartmentDto departmentDto = webClient.get()
        .uri("http://localhost:8081/api/departments/" + user.getDepartmentId())
        .retrieve().bodyToMono(DepartmentDto.class)
        .block();

        // DepartmentDto departmentDto = responseEntity.getBody();

        // System.out.println(responseEntity.getStatusCode());

        responseDto.setUserDto(userDto);
        responseDto.setDepartmentDto(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    
}
