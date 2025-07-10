package org.pets;

import org.pets.dao.HouseholdDAO;
import org.pets.dao.HouseholdDAOImpl;
import org.pets.model.Household;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Household household = new Household(1, "Adresse2");
        System.out.println(household);

        HouseholdDAO petDAO = new HouseholdDAOImpl();

        int result;
        try {
            result = petDAO.insert(household);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }
}
