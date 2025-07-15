package org.pets;

import org.pets.controller.HouseholdController;
import org.pets.dao.HouseholdDAO;
import org.pets.dao.HouseholdDAOImpl;
import org.pets.view.View;
import org.pets.enums.Actions;

public class Main {
    public static void main(String[] args) {
        HouseholdDAO householdDAO = new HouseholdDAOImpl();
        HouseholdController householdController = new HouseholdController(householdDAO);
        View view = new View();


        //ask User What to do?

        boolean exit = false;
        while (!exit) {


            Actions action = view.chooseAction();
            switch (action) {
                case NEW_HOUSEHOLD -> householdController.createHousehold(view.getAddress());
                case UPDATE_HOUSEHOLD -> view.displayHouseholds(householdController.getAllHouseholds());
                case DELETE_HOUSEHOLD -> System.out.println("Deleting household...");
                case EXIT -> exit = true;
            }
        }

        //insert into DB

//        String newAddress = "Mattersburgerstraße 36";
//        householdController.createHousehold(newAddress);





    }

}
