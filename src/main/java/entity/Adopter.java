package entity;

public class Adopter {
    private String name;

    public Adopter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // Return just the name
    }
}
