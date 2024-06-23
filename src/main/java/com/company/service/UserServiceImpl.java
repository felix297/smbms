package com.company.service;

import com.company.pojo.User;
import com.company.dao.UserDaoImpl;
import com.company.dao.UserDao;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    public UserServiceImpl () {
        userDao = new UserDaoImpl();
    }

    @Override
    public boolean userVerify (String userCode, String userPassword) {
        User user = userDao.selectByUserCode(userCode);
        return user != null && user.getUserPassword().equals(userPassword);
    }
}