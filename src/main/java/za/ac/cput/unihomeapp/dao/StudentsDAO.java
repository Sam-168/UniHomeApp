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

    public void signIn(Students student) {
   String sql = "SELECT *FROM Students WHERE email = ? AND password = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student.getEmail());
            pstmt.setString(2, student.getPassword());
               
              rs = pstmt.executeQuery();
              if(rs.next()){
                   JOptionPane.showMessageDialog(null, "Successfully Signed in");
               
              }else{
                  JOptionPane.showMessageDialog(null, "Error occured try again");
              }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (ok > 0){
            JOptionPane.showMessageDialog(null, "Successfully Signed up!!!");
        }else{
            JOptionPane.showMessageDialog(null, "Sign up was unsuccessful.");
        }

    }
}
