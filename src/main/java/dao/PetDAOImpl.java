package dao;

import entity.Pet;
import util.DBConnUtil;
import exception.InvalidPetAgeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl {

    public List<Pet> getAllPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();

        try (Connection conn = DBConnUtil.getConnection()) {
            String query = "SELECT * FROM pets";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                pets.add(new Pet(rs.getString("name"), rs.getInt("age"), rs.getString("breed")));
            }
        }

        return pets;
    }

    public void addPet(Pet pet) throws SQLException, InvalidPetAgeException {
        if (pet.getAge() <= 0) {
            throw new InvalidPetAgeException("Pet age must be a positive integer.");
        }

        String query = "INSERT INTO pets (name, age, breed) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());
            stmt.setString(3, pet.getBreed());
            stmt.executeUpdate();
        }
    }
}
