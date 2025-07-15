package org.pets.dao;

import org.example.Database;
import org.pets.connector.DBConnector;
import org.pets.model.Household;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO {
    @Override
    public Household get(int id) throws SQLException {
        String sql = "SELECT address FROM household WHERE id = ?";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, (id));
            ResultSet rs = ps.executeQuery();

            Household household = null;
            String address;
            if (rs.next()) {
                address = rs.getString("address");
                household = new Household(id, address);
            }
            return household;

        } catch (SQLException e) {
            throw new RuntimeException("failed to get household from DB", e);
        }
    }

    @Override
    public ArrayList<Household> getAll() throws SQLException {
        String sql = "SELECT * FROM household";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Household> households = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String adresse = rs.getString("adresse");
                Household household = new Household(id, adresse);
                households.add(household);
            }
            return households;
        }catch (SQLException e){
            throw new RuntimeException("failed to get all households from DB", e);
        }

    }


    @Override
    public int insert(Household household) throws SQLException {
        String sql = "INSERT INTO household (address) VALUES (?)";
        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {


            ps.setString(1, household.getAdresse());

            ps.executeUpdate();
            int id = 0;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }
            return id;


        } catch (SQLException e) {
            throw new RuntimeException("faild to insert household to DB", e);
        }
    }

    @Override
    public int update(Household household) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Household household) throws SQLException {
        return 0;
    }
}
