package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearDB {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "eu-cdbr-azure-north-e.cloudapp.net:3306/acsm_80e9ce4c997ab03";
    private static final String USER = "b04dae5a0c53bc";
    private static final String PASSWORD = "b66435d2";

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
