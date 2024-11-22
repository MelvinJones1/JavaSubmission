package entity;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
    private List<Pet> availablePets;

    // Constructor to initialize the list
    public PetShelter() {
        this.availablePets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        availablePets.add(pet);
        System.out.println("Pet added: " + pet);
    }

    public void removePet(Pet pet) {
        if (availablePets.contains(pet)) {
            availablePets.remove(pet);
            System.out.println("Pet removed: " + pet);
        } else {
            System.out.println("Pet not found in the shelter.");
        }
    }

    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets available for adoption.");
        } else {
            System.out.println("Available pets:");
            for (Pet pet : availablePets) {
                System.out.println(pet);
            }
        }
    }
}
