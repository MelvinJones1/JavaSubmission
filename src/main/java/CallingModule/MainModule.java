package CallingModule;

import entity.*;
import dao.PetDAOImpl;
import dao.DonationDAOImpl;
import dao.AdoptionEventDAOImpl;
import exception.AdoptionException;
import exception.InsufficientFundsException;
import exception.InvalidPetAgeException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    private static PetDAOImpl petDAO;
    private static DonationDAOImpl donationDAO;
    private static AdoptionEventDAOImpl adoptionEventDAO;

    public static void main(String[] args) {
        petDAO = new PetDAOImpl();
        donationDAO = new DonationDAOImpl();
        adoptionEventDAO = new AdoptionEventDAOImpl();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n --- PetPals Platform ---");
            System.out.println("1. Display Pet Listings");
            System.out.println("2. Record Donation");
            System.out.println("3. Manage Adoption Event");
            System.out.println("4. Add Pet");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayPetListings();
                    break;
                case 2:
                    recordDonation(scanner);
                    break;
                case 3:
                    manageAdoptionEvent(scanner);
                    break;
                case 4:
                    addPet(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void addPet(Scanner scanner) {
        try {
            System.out.print("Enter Pet Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter Pet Age: ");
            int age = scanner.nextInt();

            if (age < 0) {
                throw new InvalidPetAgeException("Age cannot be less than 0.");
            }

            System.out.print("Enter Pet Breed: ");
            scanner.nextLine();
            String breed = scanner.nextLine();

            System.out.print("Enter Pet Type (e.g., Dog, Cat, etc.): ");
            String type = scanner.nextLine();

            System.out.print("Enter Pet Specific Detail (e.g., color, size, etc.): ");
            String specificDetail = scanner.nextLine();

            Pet pet = new Pet(name, age, breed, type, specificDetail);

            petDAO.addPet(pet, type, specificDetail);
            System.out.println("Pet added successfully!");

        } catch (InvalidPetAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }






    private static void displayPetListings() {
        try {
            List<Pet> pets = petDAO.getAllPets();
            System.out.println("\n=== Available Pets ===");
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving pet listings: " + e.getMessage());
        }
    }

    private static void recordDonation(Scanner scanner) {
        try {
            System.out.print("Enter Donor Name: ");
            scanner.nextLine();
            String donorName = scanner.nextLine();

            System.out.print("Enter Donation Amount: ");
            float amount = scanner.nextFloat();

            if (amount < 10) {
                throw new InsufficientFundsException("Donation amount must be at least $10.");
            }

            System.out.print("Enter Donation Date (yyyy-MM-dd): ");
            String donationDate = scanner.next();

            CashDonation donation = new CashDonation(donorName, amount, donationDate);
            donationDAO.recordDonation(donation);

            System.out.println("Donation successfully recorded!");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }


    private static void manageAdoptionEvent(Scanner scanner) {
        try {
            System.out.print("Enter Event Name: ");
            scanner.nextLine();
            String eventName = scanner.nextLine();

            System.out.print("Enter Event Date (yyyy-MM-dd): ");
            String eventDate = scanner.nextLine();

            int eventId = adoptionEventDAO.insertAdoptionEvent(eventName, eventDate);

            System.out.println("Generated Event ID: " + eventId);

            if (eventId == -1) {
                throw new AdoptionException("Event creation failed.");
            }

            System.out.print("Enter your Name to Register for the Event: ");
            String participantName = scanner.nextLine();

            Adopter adopter = new Adopter(participantName);

            adoptionEventDAO.registerParticipant(eventId, adopter);

            System.out.println("Event and participant registration successful!");

        } catch (AdoptionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }



}
