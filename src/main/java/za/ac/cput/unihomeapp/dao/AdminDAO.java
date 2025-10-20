/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import za.ac.cput.unihomeapp.connection.DBConnection;
import za.ac.cput.unihomeapp.domain.Admin;

/**
 *
 * @author samuk
 */
public class AdminDAO {
   private Connection con;

    public AdminDAO() {
        this.con = DBConnection.getConnection();
    }

    public Admin signIn(Admin admin) {
        String sql = "SELECT admin_ID, username FROM Admin WHERE username = ? AND password = ?";
        Admin loggedInAdmin = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername()); 
            pstmt.setString(2, admin.getPassword());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    loggedInAdmin = new Admin(
                            rs.getInt("admin_ID"),
                            rs.getString("username")
                    );
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }

        return loggedInAdmin;
    }
}
