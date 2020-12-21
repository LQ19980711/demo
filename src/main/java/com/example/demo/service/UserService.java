package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int insertUser(User user);

    List<User> findListUser(Map params);

}
