package dao;

import entity.Adopter;

import util.DBConnUtil;

import java.sql.*;


public class AdoptionEventDAOImpl {

    public int insertAdoptionEvent(String eventName, String eventDate) throws SQLException {
        String query = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, ?)";
        int eventId = -1;

        try (Connection conn = DBConnUtil.getConnection()) {
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, eventName);
            ps.setString(2, eventDate);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Event creation failed: No rows affected.");
            }

            // Retrieve the generated event ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                eventId = rs.getInt(1);
            } else {
                throw new SQLException("Event creation failed: No ID obtained.");
            }
        }

        return eventId;
    }


    public void registerParticipant(int eventId, Adopter adopter) throws SQLException {
        String query = "INSERT INTO participants (event_id, participant_name) VALUES (?, ?)";

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, eventId);
            ps.setString(2, adopter.getName());
            ps.executeUpdate();

            System.out.println("Participant registered successfully for Event ID: " + eventId);
        }
    }

}
