package org.pets;

import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.controller.PetController;
import org.pets.dao.*;
import org.pets.enums.PersonAttributes;
import org.pets.model.Person;
import org.pets.model.Pet;
import org.pets.view.View;
import org.pets.enums.Actions;

public class Main {
    public static void main(String[] args) {
        HouseholdDAO householdDAO = new HouseholdDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl();
        HouseholdController householdController = new HouseholdController(householdDAO);
        PersonController personController = new PersonController(personDAO, householdController);
        PetController petController = new PetController(new PetDAOImpl(), personController);
        View view = new View(householdController, personController, petController);


        //ask User What to do?

        boolean exit = false;
        while (!exit) {


            Actions action = view.chooseAction();
            switch (action) {
                case NEW_HOUSEHOLD:
                    householdController.createHousehold(view.getAddress());
                    break;
                case UPDATE_HOUSEHOLD:
                    view.displayHouseholds();
                    int householdToUpdate = view.chooseId(householdController.getAllHouseholdIds());
                    householdController.updateHousehold(householdToUpdate, view.getAddress());
                    break;
                case DELETE_HOUSEHOLD:
                    view.displayHouseholds();
                    householdController.deleteHousehold(view.chooseId(householdController.getAllHouseholdIds()));
                    break;
                case NEW_PERSON:
                    Person newPerson = view.getNewPersonInfo();
                    personController.createPerson(newPerson);
                    break;
                case UPDATE_PERSON:
                    view.displayPersonsAndHouseholds();
                    Person personToUpdate = personController.getPerson(view.chooseId(personController.getAllPersonsIds()));
                    PersonAttributes attributeToUpdate = view.chooseItemToUpdate();
                    if (attributeToUpdate == PersonAttributes.HOUSEHOLD) {
                        int idOfHouseholdToUpdate;
                        if (view.updateExistingHousehold()) {
                            view.displayHouseholds();
                            idOfHouseholdToUpdate = view.chooseId(householdController.getAllHouseholdIds());
                        } else {
                            idOfHouseholdToUpdate = householdController.createHousehold(view.getAddress()).getId();
                        }
                        personToUpdate.setHouseholdId(idOfHouseholdToUpdate);
                    } else if (attributeToUpdate == PersonAttributes.FIRST_NAME) {
                        personToUpdate.setFirstName(view.getNewName());
                    } else {
                        personToUpdate.setLastName(view.getNewName());
                    }

                    personController.updatePerson(personToUpdate);
                    break;
                case DELETE_PERSON:
                    view.displayPersonsAndHouseholds();
                    Person personToDelete = personController.getPerson(view.chooseId(personController.getAllPersonsIds()));
                    personController.deletePerson(personToDelete);

                case NEW_PET:
                    Pet newPet = view.getNewPetInfo();
                    petController.createPet(newPet);
                    break;

                case UPDATE_PET:
                    view.displayPetsAndOwner();


                case EXIT:
                    exit = true;
            }
        }
    }

}
