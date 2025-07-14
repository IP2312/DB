package org.pets.dao;

import org.example.Database;
import org.pets.model.Household;

import java.sql.*;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO {
    @Override
    public Household get(int id) throws SQLException {
        try {
            Connection con = Database.getConnection();
            String sql = "SELECT address FROM household WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (id));
            ResultSet rs = ps.executeQuery();


            Household household = null;
            String address;
            if (rs.next()) {
                address = rs.getString("address");
                household = new Household(id, address);
            }


            ps.close();
            con.close();

            return household;


        } catch (SQLException e) {
            throw new RuntimeException("faild to get household from DB", e);
        }

    }

    @Override
    public List<Household> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int save(Household household) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Household household) throws SQLException {
        try {
            Connection con = DBConnector.getConnection();
            String sql = "INSERT INTO household (address) VALUES (?)";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, household.getAdresse());

            int result = ps.executeUpdate();
            ps.close();
            con.close();

            return result;
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
