package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    private RestTemplate restTemplate;
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = toUserDto(user);

        ResponseEntity<DepartmentDto> responseEntity 
        = restTemplate.getForEntity("http://localhost:8081/api/v1/departments/"
         + user.getDepartmentId() , DepartmentDto.class);

         responseDto.setUserDto(userDto);
         responseDto.setDepartmentDto(responseEntity.getBody());

         return responseDto;
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
