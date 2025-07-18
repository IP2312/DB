package org.pets.dao;

import org.pets.connector.DBConnector;
import org.pets.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAOImpl implements PersonDAO {
    @Override
    public Person get(int id) throws SQLException {
        String sql = "SELECT first_name, last_name, id_household FROM person WHERE id = ?";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int householdId = rs.getInt("id_household");
                return new Person(id, firstName, lastName, householdId);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Person> getAll() throws SQLException {
        String sql = "SELECT * FROM person";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            ResultSet rs = ps.executeQuery();
            ArrayList<Person> persons = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int householdId = rs.getInt("id_household");
                persons.add(new Person(id, firstName, lastName, householdId));
            }
            return persons;
        }
    }

    @Override
    public int insert(Person person) throws SQLException{
        String sql = "INSERT INTO person (first_name, last_name, id_household) VALUES (?, ?,?)";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
                ){
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3,person.getHouseholdId());
            ps.executeUpdate();
            int id;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()){
                if (generatedKeys.next()){
                    id = generatedKeys.getInt(1);
                }else{
                    throw new SQLException("No ID obtained.");
                }
                return id;
            }
        }

    }

    @Override
    public int update(Person person) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
