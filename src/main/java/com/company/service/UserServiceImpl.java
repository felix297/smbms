package com.company.service;

import java.util.ArrayList;
import com.company.pojo.User;
import com.company.dao.UserDaoImpl;
import com.company.dao.UserDao;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    public UserServiceImpl () {
        userDao = new UserDaoImpl();
    }

    @Override
    public User userVerify (String userCode, String userPassword) {
        User user = userDao.selectByUserCode(userCode);
        if (user == null || !user.getUserPassword().equals(userPassword)) {
            user = null;
        }

        return user;
    }

    @Override
    public boolean pwdModify (String userCode, String newUserPassword) {
        return userDao.updateUserPasswordByUserCode(userCode, newUserPassword) > 0;
    }

    @Override
    public int getUserNumber (String queryName, int queryRole) {
        return userDao.getUserNumber(queryName, queryRole);
    }

    @Override
    public ArrayList<User> selectAllUser (String queryName, int queryRole) {
        return userDao.selectAllUser(queryName, queryRole);
    }
}