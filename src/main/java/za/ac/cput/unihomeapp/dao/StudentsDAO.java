/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import za.ac.cput.unihomeapp.connection.DBConnection;
import za.ac.cput.unihomeapp.domain.Students;

/**
 *
 * @author samuk
 */
public class StudentsDAO {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstmt;

    public StudentsDAO() {

        this.con = DBConnection.getConnection();
    }

    public Students signIn(Students student) {
        String sql = "SELECT student_ID, first_name, email, phone FROM Students WHERE email = ? AND password = ?";
        Students loggedInStudent = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, student.getEmail());
            pstmt.setString(2, student.getPassword()); // hash before checking

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    
                    loggedInStudent = new Students(
                            rs.getInt("student_ID"),
                            rs.getString("first_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            null 
                    );
                    JOptionPane.showMessageDialog(null, "Successfully Signed in!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage());
        }

        return loggedInStudent;
    }

    public void signUp(Students student) {
        String sql = "INSERT INTO Students (first_name, email, student_ID, phone, password) VALUES (?, ?, ?, ?, ?)";
        int ok = -1;
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, student.getFirst_name());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getStudent_ID());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getPassword());
            ok = pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Occurred during the writing to db");
        }
        if (ok > 0) {
            JOptionPane.showMessageDialog(null, "Successfully Signed up!!!");
        } else {
            JOptionPane.showMessageDialog(null, "Sign up was unsuccessful.");
        }

    }

    public boolean isStudentIDExists(int studentID) {
        String sql = "SELECT 1 FROM Students WHERE student_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM Students WHERE email = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true if record exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
