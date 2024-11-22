package entity;



public class Pet {
    private String name;
    private int age;
    private String breed;
    private String type;
    private String specificDetail;

    public Pet(String name, int age, String breed, String type, String specificDetail) {


        this.name = name;
        this.age = age;
        this.breed = breed;
        this.type = type;
        this.specificDetail = specificDetail;
    }

    // Getters and setters...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecificDetail() {
        return specificDetail;
    }

    public void setSpecificDetail(String specificDetail) {
        this.specificDetail = specificDetail;
    }

    @Override
    public String toString() {
        return "Pet [Name=" + name + ", Age=" + age + ", Breed=" + breed + ", Type=" + type + ", SpecificDetail=" + specificDetail + "]";
    }
}
