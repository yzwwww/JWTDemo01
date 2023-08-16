package com.example.jwt01.service.impl;

import com.example.jwt01.dao.UserDao;
import com.example.jwt01.entity.User;
import com.example.jwt01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User userInfo = userDao.login(user);
        if (userInfo == null) {
            throw new RuntimeException("login failed");
        }
        return userInfo;
    }
}
