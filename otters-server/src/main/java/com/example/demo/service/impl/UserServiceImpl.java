package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user) >0 ?true:false;
    }

    @Override
    public boolean updateUserMsg(User user) {
        return userMapper.updateUserMsg(user) >0 ?true:false;
    }

    @Override
    public boolean updateUserAvator(User user) {

        return userMapper.updateUserAvator(user) >0 ?true:false;
    }

    @Override
    public boolean existUser(String userId) {
        return userMapper.existUserId(userId)>0 ? true:false;
    }

    @Override
    public boolean veritypasswd(String userId, String userPassword) {

        return userMapper.verifyPassword(userId, userPassword)>0?true:false;
    }

//    删除用户
    @Override
    public boolean deleteUser(String userId) {
        return userMapper.deleteUser(userId) >0 ?true:false;
    }

    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    @Override
    public List<User> userOfId(String userId) {

        return userMapper.userOfId(userId);
    }

    @Override
    public List<User> loginStatus(String userId) {

        return userMapper.loginStatus(userId);
    }
}
