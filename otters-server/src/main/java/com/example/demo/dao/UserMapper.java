package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int verifyPassword(String userId, String userPassword);

    int existUserId(String userId);

    int addUser(User user);

    int updateUserMsg(User record);

    int updateUserAvator(User record);

    int deleteUser(String userId);

    List<User> allUser();

    List<User> userOfId(String userId);

    List<User> loginStatus(String userName);

}
