package com.example.ms.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ms.user.dto.DepartmentDto;
import com.example.ms.user.dto.ResponseDto;
import com.example.ms.user.dto.UserDto;
import com.example.ms.user.entity.User;
import com.example.ms.user.repository.UserRepository;
import com.example.ms.user.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final RestTemplate restTemplate;
    private final WebClient webClient;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = this.userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        // ResponseEntity<DepartmentDto> responseEntity = this.restTemplate
        // .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId() 
        // , DepartmentDto.class);
        DepartmentDto departmentDto = this.webClient.get()
        
        //.uri("http://localhost:8080/api/departments/" + user.getDepartmentId())
        .uri("http://172.18.0.2:8080/api/departments/" + user.getDepartmentId())
        .retrieve().bodyToMono(DepartmentDto.class).block();

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
