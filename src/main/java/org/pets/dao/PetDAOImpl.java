package org.pets.dao;

import org.example.Database;
import org.pets.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDAOImpl implements PetDAO {
    @Override
    public Pet get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Pet> getAll() throws SQLException {
        String sql = "SELECT * FROM pet";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Pet> pets = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("namePet");
                String species = rs.getString("species");
                int owner = rs.getInt("id_person");
                pets.add(new Pet(id, name, species, owner));
            }
            return pets;
        }
    }

    @Override
    public int insert(Pet pet) throws SQLException {
        String sql = "INSERT INTO pet (namePet, id_person, species) VALUES (?, ?, ?)";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, pet.getName());
            ps.setInt(2, pet.getOwnerId());
            ps.setString(3, pet.getSpecies());
            ps.executeUpdate();
            int id;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
                return id;
            }
        }
    }

    @Override
    public int update(Pet pet) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
