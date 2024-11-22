package dao;

import entity.Donation;
import entity.CashDonation;
import util.DBConnUtil;

import java.sql.*;

public class DonationDAOImpl {

    public void recordDonation(Donation donation) throws SQLException {
        String query = "INSERT INTO donations (donor_name, amount, donation_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, donation.getDonorName());
            ps.setFloat(2, donation.getAmount());

            if (donation instanceof CashDonation) {
                ps.setString(3, ((CashDonation) donation).getDonationDate());
            }
            ps.executeUpdate();
        }
    }
}
