package org.pets;

import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.dao.HouseholdDAO;
import org.pets.dao.HouseholdDAOImpl;
import org.pets.dao.PersonDAO;
import org.pets.dao.PersonDAOImpl;
import org.pets.model.Person;
import org.pets.view.View;
import org.pets.enums.Actions;

public class Main {
    public static void main(String[] args) {
        HouseholdDAO householdDAO = new HouseholdDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl();
        HouseholdController householdController = new HouseholdController(householdDAO);
        PersonController personController = new PersonController(personDAO, householdController);
        View view = new View();


        //ask User What to do?

        boolean exit = false;
        while (!exit) {


            Actions action = view.chooseAction();
            switch (action) {
                case NEW_HOUSEHOLD:
                    householdController.createHousehold(view.getAddress());
                    break;
                case UPDATE_HOUSEHOLD:
                    view.displayHouseholds(householdController.getAllHouseholds());
                    int householdToUpdate = view.chooseId(householdController.getAllHouseholdIds());
                    householdController.updateHousehold(householdToUpdate, view.getAddress());
                    break;
                case DELETE_HOUSEHOLD:
                    view.displayHouseholds(householdController.getAllHouseholds());
                    int houseHoldToDelete = view.chooseId(householdController.getAllHouseholdIds());
                    householdController.deleteHousehold(houseHoldToDelete);
                    break;
                case NEW_PERSON:
                    Person newPerson = view.getNewPersonInfo(householdController.getAllHouseholds(),householdController.getAllHouseholdIds());
                    personController.createPerson(newPerson);
                    break;
                case UPDATE_PERSON:
                    view.displayPersonsAndHouseholds(personController.getAllPersonsWithHouseholds());
                    int personToUpdate = view.chooseId(personController.getAllPersonsIds());



                case EXIT:
                    exit = true;
            }
        }
    }

}
