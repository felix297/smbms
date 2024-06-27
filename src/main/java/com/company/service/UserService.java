package com.company.service;

import java.util.HashMap;
import java.util.ArrayList;
import com.company.pojo.User;

public interface UserService {
    public User userVerify(String userCode, String userPassword);
    public boolean pwdModify(String userCode, String newUserPassword);
    public int getUserNumber(String queryName, int queryRole);
    public ArrayList<User> selectAllUser(String queryName, int queryRole, int currentPageNum);
    public HashMap<String, String> selectByUserCode(String userCode);
    public boolean addUser(User user);
    public User getUserInfo(String userCode);
    public boolean deleteByUserCode (String userCode);
}