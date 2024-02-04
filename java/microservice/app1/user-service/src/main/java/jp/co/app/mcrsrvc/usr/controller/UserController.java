package jp.co.app.mcrsrvc.usr.controller;

import org.springframework.web.bind.annotation.RestController;

import jp.co.app.mcrsrvc.usr.dto.ResponseDto;
import jp.co.app.mcrsrvc.usr.entity.User;
import jp.co.app.mcrsrvc.usr.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = this.userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
        ResponseDto responseDto = this.userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }
}
