/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import za.ac.cput.unihomeapp.connection.DBConnection;
import za.ac.cput.unihomeapp.domain.Application;

/**
 *
 * @author samuk
 */
public class ApplicationDAO {
    private Connection con;
    private PreparedStatement pstmt;

    public ApplicationDAO() {
        this.con = DBConnection.getConnection();
    }

    public boolean saveApplication(Application app) {
    String sqlApplication = "INSERT INTO Applications (student_id, gender, campus, avarage, status) VALUES (?, ?, ?, ?, ?)";
    String sqlSubjects = "INSERT INTO Subjects (application_id, subject_name, mark) VALUES (?, ?, ?)";

    try {
        con.setAutoCommit(false); // Start transaction

        // 1️⃣ Insert into Applications
        pstmt = con.prepareStatement(sqlApplication, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, app.getStudentId());
        pstmt.setString(2, app.getGender());
        pstmt.setString(3, app.getCampus());
        pstmt.setDouble(4, app.getAverage());
        pstmt.setString(5, app.getStatus()); // e.g., "Pending" initially

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Inserting application failed, no rows affected.");
        }

        // 2️⃣ Get generated application_id (for Subjects table)
        int applicationId = -1;
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                applicationId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating application failed, no ID obtained.");
            }
        }

        // 3️⃣ Insert subjects linked to this application
        try (PreparedStatement pstmtSubjects = con.prepareStatement(sqlSubjects)) {
            ArrayList<String> subjects = app.getSubjects();
            ArrayList<Double> marks = app.getMarks();

            for (int i = 0; i < subjects.size(); i++) {
                pstmtSubjects.setInt(1, applicationId);
                pstmtSubjects.setString(2, subjects.get(i));
                pstmtSubjects.setDouble(3, marks.get(i));
                pstmtSubjects.addBatch();
            }

            pstmtSubjects.executeBatch();
        }

        con.commit(); // 
        JOptionPane.showMessageDialog(null, "Application successfully submitted!");
        return true;

    } catch (SQLException e) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Rollback failed: " + ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        return false;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage());
        return false;

    } finally {
        try {
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
   public ArrayList<Application> getAllApplications() {
    ArrayList<Application> applications = new ArrayList<>();

    String sql = """
        SELECT 
            a.application_id,
            a.student_id,
            CONCAT(s.first_name, ' ', s.last_name) AS full_name,
            a.avarage,
            a.campus,
            a.status
        FROM Applications a
        JOIN Students s ON a.student_id = s.student_id
    """;

    try (PreparedStatement pstmt = con.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Application app = new Application();
            app.setApplicationId(rs.getInt("application_id"));
            app.setStudentId(rs.getInt("student_id"));
            app.setFullName(rs.getString("full_name"));
            app.setAverage(rs.getDouble("avarage"));
            app.setCampus(rs.getString("campus"));
            app.setStatus(rs.getString("status"));

            applications.add(app);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return applications;
}
   public boolean updateApplicationStatus(int applicationId, String status) {
    String sql = "UPDATE Applications SET status = ? WHERE application_id = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, status);
        pstmt.setInt(2, applicationId);
        int affected = pstmt.executeUpdate();
        return affected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   

}
