package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public List<User> findListUser(Map params) {
        return userMapper.findListUser(params);
    }


    @Transactional(rollbackFor=Exception.class)
    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

}
