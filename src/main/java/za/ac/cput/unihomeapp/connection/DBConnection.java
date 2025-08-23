/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.connection;

import java.sql.*;

/**
 *
 * @author samuk
 */
public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/unihomeaccommodation";
    private static final String USER = "root";
    private static final String PASSWORD = "Nomboniso@55";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
