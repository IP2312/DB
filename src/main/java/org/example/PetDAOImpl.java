package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PetDAOImpl implements PetDAO{
    @Override
    public Pet get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Pet pet = null;
        String sql = "SELECT id, name, owner_id, gender, age FROM pets WHERE id = ?";
        P
        return pet;
    }

    @Override
    public List<Pet> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int save(Pet pet) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Pet pet) throws SQLException {
        return 0;
    }

    @Override
    public int update(Pet pet) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Pet pet) {
        return 0;
    }
}
