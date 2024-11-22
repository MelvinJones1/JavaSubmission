package entity;

public abstract class Donation {
    private String donorName;
    private float amount;

    public Donation(String donorName, float amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public abstract void recordDonation();
}
