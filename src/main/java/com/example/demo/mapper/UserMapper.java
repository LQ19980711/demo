package com.example.demo.mapper;

import com.example.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int insertUser(User user);

    List<User> findListUser(Map params);
}
