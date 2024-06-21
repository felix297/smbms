package com.company.dao;

import com.company.pojo.User;

public interface UserDao {
    public User selectByUserCode (String userCode);
}