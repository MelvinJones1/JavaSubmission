package entity;

public class CashDonation extends Donation {
    private String donationDate;

    public CashDonation(String donorName, float amount, String donationDate) {
        super(donorName, amount);
        this.donationDate = donationDate;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public void recordDonation() {
        System.out.println("Cash donation recorded: " + getDonorName() + " donated " + getAmount() + " on " + donationDate);
    }
}
