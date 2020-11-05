package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean updateUserMsg(User user);

    boolean updateUserAvator(User user);

    boolean existUser(String userId);

    boolean veritypasswd(String userId, String userPassword);

    boolean deleteUser(String userId);

    List<User> allUser();

    List<User> userOfId(String userId);

    List<User> loginStatus(String userId);

}
