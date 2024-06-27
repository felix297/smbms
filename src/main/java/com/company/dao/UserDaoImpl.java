package com.company.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.company.util.Constant;
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
    public int deleteByUserCode (String userCode) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "delete from smbms_user where userCode = ?;";
        Object[] params = {userCode};

        return BaseDao.executeUpdate(connection, preparedStatement, sql, params);
    }

    @Override
    public int addUser (User user) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userCode = user.getUserCode();
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String gender = user.getGender();
        Date birthday = user.getBirthday();
        String phone = user.getPhone();
        String address = user.getAddress();
        String userRole = user.getUserRole();
        String createdBy = "1";
        String creationDate = dateFormat.format(new Date());
        String modifyBy = "1";
        String modifyDate = dateFormat.format(new Date());
        String sql = "insert into smbms_user values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {null, userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate};
        return BaseDao.executeUpdate(connection, preparedStatement, sql, params);
    }

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
    public User getUserInfo (String userCode) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        String sql = "select smbms_user.*, smbms_role.roleName as userRoleName from smbms_user, smbms_role where smbms_user.userRole = smbms_role.id and userCode=?;";
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
                user.setUserRoleName(res.getString(14));
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
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        res = selectAllUtil (connection, preparedStatement, res, queryName, queryRole, sql, 0);

        try {
            while(res != null && res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(connection, preparedStatement, res);
        }

        return count;
    }

    @Override
    public ArrayList<User> selectAllUser (String queryName, int queryRole, int currentPageNum) {
        String sql = "select smbms_user.*, smbms_role.roleName as userRoleName from smbms_user, smbms_role where smbms_user.userRole = smbms_role.id ";
        ArrayList<User> userList = null;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        res = selectAllUtil (connection, preparedStatement, res, queryName, queryRole, sql, currentPageNum);

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
        } finally {
            JDBCUtil.closeAll(connection, preparedStatement, res);
        }
        return userList;
    }

    public ResultSet selectAllUtil (Connection connection, PreparedStatement preparedStatement, ResultSet res, String queryName, int queryRole, String sql, int currentPageNum) {
        ArrayList<Object> params = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(queryName)) {
            sql = sql.concat("and smbms_user.userName like ? ");
            params.add("%" + queryName + "%");
        }

        if (queryRole > 0) {
            sql = sql.concat("and smbms_user.userRole = ? ");
            params.add(queryRole);
        }

        if (currentPageNum > 0) {
            sql = sql + "order by creationDate DESC limit ?,? ";

            params.add((currentPageNum - 1) * Constant.getPageSize());
            params.add(Constant.getPageSize());
        }

        res = BaseDao.executeQuery(connection, preparedStatement, res, sql, params.toArray());

        return res;
    }
}