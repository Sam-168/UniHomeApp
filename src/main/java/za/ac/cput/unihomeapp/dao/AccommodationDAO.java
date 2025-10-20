/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.dao;

/**
 *
 * @author samuk
 */

import za.ac.cput.unihomeapp.connection.DBConnection;
import za.ac.cput.unihomeapp.domain.Accommodation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AccommodationDAO {
    private Connection con;

    public AccommodationDAO() {
        this.con = DBConnection.getConnection();
    }

    // Fetch accommodation by application_id
    public Accommodation getAccommodationByApplicationId(int applicationId) {
        String sql = "SELECT a.accommodation_id, a.residence_id, a.landlord_id, a.room_type, a.price, a.availability_status, a.lease_duration " +
                     "FROM Accommodation a " +
                     "INNER JOIN Applications app ON app.application_id = ? " +
                     "WHERE a.accommodation_id = app.accommodation_id"; // assumes you store accommodation_id in Applications table

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, applicationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Accommodation(
                        rs.getInt("accommodation_id"),
                        rs.getInt("residence_id"),
                        rs.getInt("landlord_id"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getString("availability_status"),
                        rs.getString("lease_duration")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // no accommodation found
    }
    public Accommodation getAccommodationByStudentId(int studentId) {
    Accommodation acc = null;
    String sql = "SELECT a.* FROM accommodation a "
               + "JOIN application ap ON a.accommodation_id = ap.accommodation_id "
               + "WHERE ap.student_id = ?";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            acc = new Accommodation(
                rs.getInt("accommodation_id"),
                rs.getInt("residence_id"),
                rs.getInt("landlord_id"),
                rs.getString("room_type"),
                rs.getDouble("price"),
                rs.getString("availability_status"),
                rs.getString("lease_duration")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return acc;
}

}
