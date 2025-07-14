package org.pets.controller;

import org.pets.dao.HouseholdDAO;
import org.pets.model.Household;

import java.sql.SQLException;

public class HouseholdController {
    private final HouseholdDAO householdDAO;


    public HouseholdController(HouseholdDAO householdDAO) {
        this.householdDAO = householdDAO;
    }

    public void createHousehold(String adresse) {
        Household household = new Household(adresse);
        try {
            householdDAO.insert(household);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHouseholdAddress(int id){

        try {
           Household household = householdDAO.get(id);
            return household.getAdresse();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
