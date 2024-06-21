package com.company.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.company.pojo.User;
import org.junit.jupiter.api.Test;
import com.company.util.JDBCUtil;

public class BaseDao {
    public static ResultSet selectByAttribute (Connection connection, PreparedStatement preparedStatement, ResultSet result, String sql, Object[] params) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}