package com.company.service;

import java.util.HashMap;
import java.util.ArrayList;
import com.company.pojo.User;
import com.company.dao.UserDaoImpl;
import com.company.dao.UserDao;
import com.mysql.cj.util.StringUtils;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public UserServiceImpl () {
        userDao = new UserDaoImpl();
    }

    @Override
    public boolean insert (User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean deleteByUserCode (String userCode) {
        return userDao.deleteByUserCode(userCode) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user) > 0;
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
    public HashMap<String, String> selectByUserCode (String userCode) {
        User user = userDao.selectByUserCode(userCode);
        HashMap<String, String> res = new HashMap<>();
        if (StringUtils.isNullOrEmpty(userCode)) {
            res.put("userCode", "exist");
        } else {
            if (user == null) {
                res.put("userCode", "notexist");
            } else {
                res.put("userCode", "exist");
            }
        }
        return res;
    }

    @Override
    public User getUserInfo (String userCode) {
        return userDao.getUserInfo(userCode);
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
    public ArrayList<User> selectAllUser (String queryName, int queryRole, int currentPageNum) {
        return userDao.selectAllUser(queryName, queryRole, currentPageNum);
    }
}