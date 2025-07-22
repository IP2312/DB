package org.pets.dao;

import org.pets.connector.DBConnector;
import org.pets.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDAOImpl implements PetDAO {
    @Override
    public Pet get(int id) throws SQLException {
        String sql = "SELECT namePet, id_person, species FROM pet WHERE id = ?";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Pet pet = null;
            if (rs.next()){
                String name = rs.getString("namePet");
                String species = rs.getString("species");
                int owner = rs.getInt("id_person");
                pet = new Pet(id, name, species, owner);

            }
            return pet;
        }
    }

    @Override
    public ArrayList<Pet> getAll() throws SQLException {
        String sql = "SELECT * FROM pet";
        try (
                Connection con = DBConnector.getConnection();
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
                Connection con = DBConnector.getConnection();
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
        String sql = "UPDATE pet SET namePet = ?, id_person = ?, species = ? WHERE id = ?";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, pet.getName());
            ps.setInt(2, pet.getOwnerId());
            ps.setString(3, pet.getSpecies());
            ps.setInt(4, pet.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM pet WHERE id = ?";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}
