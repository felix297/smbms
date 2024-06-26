package com.company.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.company.pojo.User;
import com.company.util.JDBCUtil;
import com.mysql.cj.util.StringUtils;
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
                user.setBirthday(res.getDate(6));
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
    public int getUserNumber (String queryName, int queryRole) {
        Integer count = null;
        String sql = "select count(1) from smbms_user, smbms_role where smbms_user.userRole = smbms_role.id ";
        ResultSet res = selectAllUtil (queryName, queryRole, sql);

        try {
            while(res != null && res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public ArrayList<User> selectAllUser (String queryName, int queryRole) {
        String sql = "select smbms_user.*, smbms_role.roleName as userRoleName from smbms_user, smbms_role where smbms_user.userRole = smbms_role.id ";
        ArrayList<User> userList = null;
        ResultSet res = selectAllUtil (queryName, queryRole, sql);

        try {
            if (res != null) {
                userList = new ArrayList<>();
                while (res.next()) {
                    User user = new User();
                    user.setId(res.getInt(1));
                    user.setUserCode(res.getString(2));
                    user.setUserName(res.getString(3));
                    user.setUserPassword(res.getString(4));
                    user.setGender(res.getString(5));
                    user.setBirthday(res.getDate(6));
                    user.setPhone(res.getString(7));
                    user.setAddress(res.getString(8));
                    user.setUserRole(res.getString(9));
                    user.setCreatedBy(res.getString(10));
                    user.setCreationDate(res.getString(11));
                    user.setModifyBy(res.getString(12));
                    user.setModifyDate(res.getString(13));
                    user.setUserRoleName(res.getString(14));
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Test
    public void test () {
        ArrayList<User> userList = selectAllUser(null, 3);
        for (User ele : userList) {
            System.out.println(ele.getAge());
        }
    }

    public ResultSet selectAllUtil (String queryName, int queryRole, String sql) {
        ArrayList<Object> params = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(queryName)) {
            sql = sql.concat("and smbms_user.userName like ?");
            params.add("%" + queryName + "%");
        }

        if (queryRole > 0) {
            sql = sql.concat("and smbms_user.userRole = ?");
            params.add(queryRole);
        }

        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        res = BaseDao.executeQuery(connection, preparedStatement, res, sql, params.toArray());

        return res;
    }
}