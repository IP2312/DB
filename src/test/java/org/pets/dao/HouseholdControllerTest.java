package org.pets.dao;

import org.example.Database;
import org.junit.jupiter.api.Test;
import org.pets.controller.HouseholdController;
import org.pets.model.Household;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HouseholdControllerTest {


    @Test
    public void createHouseholdTest() {
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        Household household = householdController.createHousehold("Badergasse 13");

        assertEquals("Badergasse 13", householdController.getHouseholdAddress(household.getId()));

    }

    @Test
    public void getHouseholdAddressTest() {
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        Household household = householdController.createHousehold("Badergasse 33");

        assertEquals(household.getAdresse(), householdController.getHouseholdAddress(household.getId()));
        householdController.deleteHousehold(household.getId());
        assertThrows(RuntimeException.class, () -> householdController.getHouseholdAddress(household.getId()));
    }

    @Test
    public void getAllHouseholdsTest() {
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        ArrayList<Household> households = householdController.getAllHouseholds();
        String sql = "SELECT COUNT(*) FROM household";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                assertEquals(count , households.size());
            }
        }catch(SQLException e){
            throw new RuntimeException("failed to get all households from DB", e);
        }
    }


    @Test
    public void getAllHouseholdsIdsTest(){
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "Select id from household order by id asc";
        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id  = rs.getInt("id");
                ids.add(id);
            }
            ArrayList<Integer> sortedList = householdController.getAllHouseholdIds() ;
            Collections.sort(sortedList);
            assertEquals(sortedList, ids);
        }catch(SQLException e){
            throw new RuntimeException("failed to get all households ids from DB", e);
        }
    }

    @Test
    public void updateHouseholdTest(){
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        Household household = householdController.createHousehold("Badergasse 13");
        householdController.updateHousehold(household.getId(), "Badergasse 14");
        assertEquals("Badergasse 14", householdController.getHouseholdAddress(household.getId()));
        householdController.deleteHousehold(household.getId());
    }
    @Test
    public void deleteHouseholdTest(){
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        Household household = householdController.createHousehold("Badergasse 13");
        int nrHouseholds = householdController.getAllHouseholds().size();
        householdController.deleteHousehold(household.getId());
        assertEquals(nrHouseholds-1, householdController.getAllHouseholds().size());
        assertThrows(RuntimeException.class, () -> householdController.getHouseholdAddress(household.getId()));
    }

}
