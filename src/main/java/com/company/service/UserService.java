package com.company.service;

import java.util.ArrayList;
import com.company.pojo.User;

public interface UserService {
    public User userVerify(String userCode, String userPassword);
    public boolean pwdModify(String userCode, String newUserPassword);
    public int getUserNumber(String queryName, int queryRole);
    public ArrayList<User> selectAllUser(String queryName, int queryRole, int currentPageNum);
}