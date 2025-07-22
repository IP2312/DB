package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    @Override
    public Pet get(int id) throws SQLException {
        Connection con = DBConnector.getConnection();
        Pet pet = null;
        String sql = "SELECT id, name, ownerId, gender, age FROM pet WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            int ownerId = rs.getInt("ownerId");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");

            pet = new Pet(oid, name, ownerId, gender, age);
        }
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
        Connection con = DBConnector.getConnection();

        String sql = "INSERT INTO pet (name, ownerId, gender, age) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pet.getName());
        ps.setInt(2, pet.getOwnerId());
        ps.setString(3, pet.getGender());
        ps.setInt(4, pet.getAge());

        int result = ps.executeUpdate();
        ps.close();
        con.close();

        return result;
    }

    @Override
    public int update(Pet pet) throws SQLException {


        Connection con = DBConnector.getConnection();
        String sql = "UPDATE pet SET name = ?, ownerId = ?, gender = ?, age = ? WHERE id = ? ";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pet.getName());
        ps.setInt(2, pet.getOwnerId());
        ps.setString(3, pet.getGender());
        ps.setInt(4, pet.getAge());
        ps.setInt(5, pet.getId());

        int result = ps.executeUpdate();
        ps.close();
        con.close();

        return result;
    }

    @Override
    public int delete(Pet pet) throws SQLException{
        Connection con = DBConnector.getConnection();

        String sql = "DELETE FROM pet WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, pet.getId());
        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }
}
