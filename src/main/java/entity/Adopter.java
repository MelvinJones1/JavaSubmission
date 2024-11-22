package entity;


public class Adopter implements IAdoptable {
    private String name;

    public Adopter(String name) {
        this.name = name;
    }

    @Override
    public void adopt() {
        System.out.println(name + " is adopting a pet!");
    }

    @Override
    public String toString() {
        return "Adopter [name=" + name + "]";
    }
}
