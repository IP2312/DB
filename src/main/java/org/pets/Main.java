package org.pets;

import org.pets.controller.HouseholdController;
import org.pets.dao.HouseholdDAO;
import org.pets.dao.HouseholdDAOImpl;

public class Main {
    public static void main(String[] args) {
        HouseholdDAO householdDAO = new HouseholdDAOImpl();
        HouseholdController householdController = new HouseholdController(householdDAO);


        //ask User What to do?

        //insert into DB

        String newAddress = "Mattersburgerstraße 35";
        householdController.createHousehold(newAddress);

        System.out.println("Main: ");
        System.out.println( householdController.getHouseholdAddress(1));





    }

}
