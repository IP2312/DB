package org.pets;

import org.pets.dao.HouseholdDAO;
import org.pets.dao.HouseholdDAOImpl;
import org.pets.model.Household;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        HouseholdDAO householdDAO = new HouseholdDAOImpl();


        //ask User What to do?











        Household household = new Household(1, "Adresse5");



        int result;
        try {
            result = householdDAO.insert(household);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int sum(int a, int b) {
        return a + b;
    }
}
