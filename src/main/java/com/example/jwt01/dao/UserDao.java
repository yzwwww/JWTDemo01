package com.example.jwt01.dao;

import com.example.jwt01.entity.User;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
    User login(User user);

}
