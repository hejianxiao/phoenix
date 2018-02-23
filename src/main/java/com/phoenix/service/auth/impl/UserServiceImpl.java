package com.phoenix.service.auth.impl;

import com.phoenix.dataobject.auth.User;
import com.phoenix.dataobject.auth.mapper.UserMapper;
import com.phoenix.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
@Service
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUserName(String userName, String password) {
        return userMapper.selectByUserName(userName,password);
    }

}
