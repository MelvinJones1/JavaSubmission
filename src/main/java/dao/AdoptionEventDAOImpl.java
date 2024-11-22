package dao;

import entity.Adopter;

import util.DBConnUtil;

import java.sql.*;

public class AdoptionEventDAOImpl {

    // Insert the adoption event and return the generated event ID
    public int insertAdoptionEvent(String eventName, String eventDate) throws SQLException {
        String query = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, ?)";
        int eventId = -1;

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, eventName);
            ps.setString(2, eventDate);
            ps.executeUpdate();

            // Retrieve the generated event ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                eventId = rs.getInt(1);
            }
        }
        return eventId;
    }

    // Register the participant for the event using the generated event ID
    public void registerParticipant(int eventId, Adopter adopter) throws SQLException {
        String query = "INSERT INTO participants (event_id, participant_name) VALUES (?, ?)";

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, eventId);  // Use the event ID that was generated during event insertion
            ps.setString(2, adopter.toString());  // Store participant's name
            ps.executeUpdate();
        }
    }
}
