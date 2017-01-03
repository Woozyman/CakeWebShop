package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearDB {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "eu-cdbr-azure-north-e.cloudapp.net";
    private static final String USER = "b5d2060bd7b20b";
    private static final String PASSWORD = "b7d9734d";

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
