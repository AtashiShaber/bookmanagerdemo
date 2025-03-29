package com.shaber.bookmanager.controller;

import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.pojo.User;
import com.shaber.bookmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userServiceImpl;

    //登录
    @GetMapping
    public ResultValue<User> login(String username, String password) throws NoSuchAlgorithmException {
        return userServiceImpl.selectUser(username,password);
    }

    //注册
    @PostMapping
    public ResultValue register(User user) throws NoSuchAlgorithmException {
        return userServiceImpl.register(user);
    }

    //修改密码
    @PutMapping
    public ResultValue updatePassword(String username, String password , String newPassword) throws NoSuchAlgorithmException {
        return userServiceImpl.updatePassword(username,password,newPassword);
    }

    //修改用户名
    public ResultValue updateUsername(String username, String newUsername) {
        return userServiceImpl.updateUsername(username,newUsername);
    }

    @GetMapping("/{uid}")
    //通过uid查询用户名
    public ResultValue<String> getUsername(String uid) {
        return userServiceImpl.searchUserByUid(uid);
    }
}
