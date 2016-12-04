/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Michael
 */
public class DB {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cakeWebShop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static Connection conn = null;
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    public static void closeConnection(Connection conn){
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException ex){
              ex.printStackTrace();
           }
    }
    public static void main(String[] args) throws SQLException{
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while(rs.next()){
            int id = rs.getInt("userid");
            String firstname = rs.getString("firstname");
            String password = rs.getString("password");
            System.out.println(id+": "+firstname+": "+password);
        }
    }

}
