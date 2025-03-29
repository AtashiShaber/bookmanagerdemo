package com.shaber.bookmanager.service;

import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.pojo.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    //查找用户
    ResultValue<User> selectUser(String username, String password) throws NoSuchAlgorithmException;
    //注册
    ResultValue register(User user) throws NoSuchAlgorithmException;
    //修改密码
    ResultValue updatePassword(String username, String password , String newPassword) throws NoSuchAlgorithmException;
    //修改用户名
    ResultValue updateUsername(String username, String newUsername);

    ResultValue<String> searchUserByUid(String uid);
}
