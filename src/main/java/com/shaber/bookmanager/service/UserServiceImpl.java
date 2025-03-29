package com.shaber.bookmanager.service;

import com.shaber.bookmanager.mapper.UserMapper;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.pojo.User;
import com.shaber.bookmanager.utils.MD5WithSalt;
import com.shaber.bookmanager.utils.RandomUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultValue<User> selectUser(String username, String password) throws NoSuchAlgorithmException {
        ResultValue<User> resultValue = new ResultValue<>();
        //首先查询用户名是否存在
        if (userMapper.existsUsername(username) == null){
            resultValue.setResult(false);
            resultValue.setMessage("用户名不存在！");
            return resultValue;
        }
        //再查询用户密码是否正确
        User user = userMapper.existsUsernameAndPassword(username, MD5WithSalt.encryptPasswordWithSalt(password));
        if (user == null){
            resultValue.setResult(false);
            resultValue.setMessage("密码错误！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setT(user);
        resultValue.setMessage("查询成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue register(User user) throws NoSuchAlgorithmException {
        ResultValue resultValue = new ResultValue();
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("password",MD5WithSalt.encryptPasswordWithSalt(user.getPassword()));
        map.put("uid", RandomUUIDUtil.getUUID(32));
        map.put("img",user.getImg());
        if (userMapper.register(map) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("注册失败！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("注册成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue updatePassword(String username, String password, String newPassword) throws NoSuchAlgorithmException {
        ResultValue resultValue = new ResultValue();
        //先检查账户密码是否正确
        User user = userMapper.existsUsernameAndPassword(username, MD5WithSalt.encryptPasswordWithSalt(password));
        if (user == null){
            resultValue.setResult(false);
            resultValue.setMessage("账户密码错误！");
            return resultValue;
        }
        //修改密码
        if (userMapper.updatePassword(username, MD5WithSalt.encryptPasswordWithSalt(password)) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("密码修改失败！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("密码修改成功");
        return resultValue;
    }

    @Override
    public ResultValue updateUsername(String username, String newUsername) {
        ResultValue resultValue = new ResultValue();
        if (userMapper.existsUsername(username) == null){
            resultValue.setResult(false);
            resultValue.setMessage("用户名错误！");
            return resultValue;
        }
        if (userMapper.updateUsername(username, newUsername) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("用户名修改出错！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("用户名修改成功");
        return resultValue;
    }

    @Override
    public ResultValue<String> searchUserByUid(String uid) {
        ResultValue<String> resultValue = new ResultValue<>();
        resultValue.setT(userMapper.existsUid(uid).getUsername());
        resultValue.setResult(true);
        resultValue.setMessage("查询完成");
        return resultValue;
    }
}
