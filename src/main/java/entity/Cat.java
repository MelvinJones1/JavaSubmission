package entity;


public class Cat extends Pet {
    private String catColor;

    public Cat(String name, int age, String breed, String type, String specificDetail, String catColor) {
        super(name, age, breed, type, specificDetail);
        this.catColor = catColor;
    }

    public String getCatColor() {
        return catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }

    @Override
    public String toString() {
        return super.toString() + ", CatColor=" + catColor;
    }
}
