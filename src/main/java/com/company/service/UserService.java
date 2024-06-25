package com.company.service;

import com.company.pojo.User;

public interface UserService {
    public User userVerify(String userCode, String userPassword);
    public boolean pwdModify(String userCode, String newUserPassword);
}