package com.company.dao;

import java.sql.PreparedStatement;
import com.company.pojo.User;
import com.company.util.JDBCUtil;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import com.company.dao.BaseDao;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectByUserCode (String userCode) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        String sql = "select * from smbms_user where userCode = ?;";
        User user = new User();
        Object[] params = {userCode};
        res = BaseDao.selectByAttribute(connection, preparedStatement, res, sql, params);
        try {
            while (res != null && res.next()) {
                user.setId(res.getInt(1));
                user.setUserCode(res.getString(2));
                user.setUserName(res.getString(3));
                user.setUserPassword(res.getString(4));
                user.setGender(res.getString(5));
                user.setBirthday(res.getString(6));
                user.setPhone(res.getString(7));
                user.setAddress(res.getString(8));
                user.setUserRole(res.getString(9));
                user.setCreatedBy(res.getString(10));
                user.setCreationDate(res.getString(11));
                user.setModifyBy(res.getString(12));
                user.setModifyDate(res.getString(13));
//                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(connection, preparedStatement, res);
        }

        return user;
    }
}