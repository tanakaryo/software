package jp.co.app.mcrsrvc.usr.service;

import jp.co.app.mcrsrvc.usr.dto.ResponseDto;
import jp.co.app.mcrsrvc.usr.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}
