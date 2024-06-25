package com.company.dao;

import com.company.pojo.User;

public interface UserDao {
    public User selectByUserCode (String userCode);
    public int updateUserPasswordByUserCode (String userCode, String userPassword);
    public int getUserNumber ();
}