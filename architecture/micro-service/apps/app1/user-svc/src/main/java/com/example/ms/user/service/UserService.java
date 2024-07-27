package com.example.ms.user.service;

import com.example.ms.user.dto.ResponseDto;
import com.example.ms.user.entity.User;

public interface UserService {

    User saveUser(User user);
    ResponseDto getUser(Long userId);
}
