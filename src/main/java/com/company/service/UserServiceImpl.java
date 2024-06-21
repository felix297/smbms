package com.company.service;

import com.company.pojo.User;
import com.company.dao.UserDaoImpl;
import com.company.dao.UserDao;
import org.junit.jupiter.api.Test;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    public UserServiceImpl () {
        userDao = new UserDaoImpl();
    }

    @Test
    @Override
    public void userVerify () {
        User user = userDao.selectByUserCode("dfasd");
        System.out.println(user.toString());
    }
}