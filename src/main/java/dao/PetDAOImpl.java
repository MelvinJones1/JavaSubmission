package dao;

import entity.Pet;
import util.DBConnUtil;

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
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                String type = rs.getString("type");
                String specificDetail = rs.getString("specific_detail");

                name = (name == null) ? "Unknown" : name;
                breed = (breed == null) ? "Unknown" : breed;
                type = (type == null) ? "Unknown" : type;
                specificDetail = (specificDetail == null) ? "Unknown" : specificDetail;

                Pet pet = new Pet(name, age, breed, type, specificDetail);
                pets.add(pet);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            throw e;
        }

        return pets;
    }



    public void addPet(Pet pet, String type, String specificDetail) throws SQLException {
        String query = "INSERT INTO pets (name, age, breed, type, specific_detail) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());
            stmt.setString(3, pet.getBreed());
            stmt.setString(4, type);
            stmt.setString(5, specificDetail);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No pet added to the database.");
            }
        }
    }



}
