package com.company.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.company.pojo.User;
import com.company.util.JDBCUtil;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.Connection;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectByUserCode (String userCode) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        String sql = "select * from smbms_user where userCode = ?;";
        User user = null;
        Object[] params = {userCode};
        res = BaseDao.executeQuery(connection, preparedStatement, res, sql, params);
        try {
            while (res != null && res.next()) {
                user = new User();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(connection, preparedStatement, res);
        }

        return user;
    }

    @Override
    public int updateUserPasswordByUserCode (String userCode, String newUserPassword) {
        Integer modifiedCount = null;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "update smbms_user set userPassword = ? where userCode = ?;";
            Object[] params = {newUserPassword, userCode};
            modifiedCount = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(connection, preparedStatement);
        }
        return modifiedCount;
    }

    @Override
    public int getUserNumber () {
        Integer count = null;
        String sql = "select count(1) from smbms_user;";

        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        res = BaseDao.executeQuery(connection, preparedStatement, res, sql, null);

        try {
            while(res != null && res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}