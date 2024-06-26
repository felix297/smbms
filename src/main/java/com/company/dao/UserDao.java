package com.company.dao;

import java.util.ArrayList;
import com.company.pojo.User;

public interface UserDao {
    public User selectByUserCode (String userCode);
    public int updateUserPasswordByUserCode (String userCode, String userPassword);
    public int getUserNumber (String queryName, int queryRole);
    public ArrayList<User> selectAllUser(String queryName, int queryRole, int currentPageNum);
}