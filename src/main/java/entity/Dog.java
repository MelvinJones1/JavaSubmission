package entity;


public class Dog extends Pet {
    private String dogBreed;

    public Dog(String name, int age, String breed, String type, String specificDetail, String dogBreed)  {
        super(name, age, breed, type, specificDetail);  // Pass to superclass Pet
        this.dogBreed = dogBreed;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    @Override
    public String toString() {
        return super.toString() + ", DogBreed=" + dogBreed;  // Include 'dogBreed' along with inherited attributes
    }
}
