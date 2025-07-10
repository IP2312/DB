package org.pets.dao;

import org.example.Database;
import org.pets.model.Household;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO{
    @Override
    public Household get(int id) throws SQLException {
        return null;
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
        Connection con = Database.getConnection();
        String sql = "INSERT INTO household (address) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, household.getAdresse());

        int result = ps.executeUpdate();
        ps.close();
        con.close();

        return result;
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
