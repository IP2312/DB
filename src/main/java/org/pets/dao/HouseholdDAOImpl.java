package org.pets.dao;

import org.example.DBConnector;
import org.pets.model.Household;

import java.sql.*;
import java.util.ArrayList;

public class HouseholdDAOImpl implements HouseholdDAO {
    @Override
    public Household get(int id) throws SQLException {
        String sql = "SELECT address FROM household WHERE id = ?";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, (id));
            ResultSet rs = ps.executeQuery();

            Household household = null;
            String address;
            if (rs.next()) {
                address = rs.getString("address");
                household = new Household(id, address);
            }
            return household;
        }
    }

    @Override
    public ArrayList<Household> getAll() throws SQLException {
        String sql = "SELECT * FROM household";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Household> households = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String adresse = rs.getString("address");
                Household household = new Household(id, adresse);
                households.add(household);
            }
            return households;
        }

    }


    @Override
    public int insert(Household household) throws SQLException {
        String sql = "INSERT INTO household (address) VALUES (?)";
        try (
                Connection con = org.pets.connector.DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {


            ps.setString(1, household.getAdresse());
            ps.executeUpdate();
            int id;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }
            return id;
        }
    }

    @Override
    public int update(Household household) throws SQLException {
        String sql = "UPDATE household SET address = ? WHERE id = ?";
        try (Connection con = org.pets.connector.DBConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
             ){
            ps.setString(1, household.getAdresse());
            ps.setInt(2, household.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM household WHERE id = ?";
        try (Connection con = org.pets.connector.DBConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
             ){
            ps.setInt(1, id);
            return ps.executeUpdate();
        }

    }
}
