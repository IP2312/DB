package org.pets.controller;

import org.pets.dao.HouseholdDAO;
import org.pets.model.Household;

import java.sql.SQLException;
import java.util.ArrayList;


public class HouseholdController {
    private final HouseholdDAO householdDAO;


    public HouseholdController(HouseholdDAO householdDAO) {
        this.householdDAO = householdDAO;
    }

    public Household createHousehold(String adresse) {
        Household household = new Household(adresse);
        try {
            System.out.println("Household: ");
            int id = householdDAO.insert(household);
            household.setId(id);
            System.out.println(household);
            return household;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHouseholdAddress(int id) {

        try {
            Household household = householdDAO.get(id);
            return household.getAdresse();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Household> getAllHouseholds() {
        try {
            return householdDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> getAllHouseholdIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Household household : getAllHouseholds()) {
            ids.add(household.getId());
        }
        return ids;
    }

    public void updateHousehold(int id, String adresse) {
        Household household = new Household(id, adresse);
        try {
            householdDAO.update(household);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteHousehold(int id) {
        try {
            householdDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
