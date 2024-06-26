package com.company.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.company.util.JDBCUtil;
import com.company.pojo.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao {
    @Override
    public ArrayList<Role> selectAllRole () {
        ArrayList<Role> roleList = null;
        String sql = "select * from smbms_role;";

        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;

        res = BaseDao.executeQuery(connection, preparedStatement, res, sql, null);

        if (res != null) {
            roleList = new ArrayList<>();
            try {
                while (res.next()) {
                    Role role = new Role();
                    role.setId(res.getInt(1));
                    role.setRoleCode(res.getString(2));
                    role.setRoleName(res.getString(3));
                    role.setCreatedBy(res.getString(4));
                    role.setCreationDate(res.getString(5));
                    role.setModifyBy(res.getString(6));
                    role.setModifyDate(res.getString(7));
                    roleList.add(role);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roleList;
    }

    @Test
    public void test () {
        ArrayList<Role> roleList = selectAllRole();
        for (Role ele : roleList) {
            System.out.println(ele.toString());
        }
    }
}