package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/kata";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Vano_1506_www_2000";

    public static Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("We're connected");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR Connect");
        }
        return connection;
    }
}
