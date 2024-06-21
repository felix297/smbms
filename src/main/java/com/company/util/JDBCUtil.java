package com.company.util;

import com.company.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import java.io.InputStream;

public class JDBCUtil {
    @Test
    public void selectAll () {

    }

    public static Connection getConnection () {
        InputStream input = ClassLoader.getSystemResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(input);
            String URL = properties.getProperty("URL");
            String USERNAME = properties.getProperty("USERNAME");
            String PASSWORD = properties.getProperty("PASSWORD");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void closeAll (Connection connection, Statement statement, ResultSet result) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeAll (Connection connection, Statement statement) {
        ResultSet result = null;
        closeAll(connection, statement, result);
    }

}