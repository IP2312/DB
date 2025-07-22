package org.pets;

import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.controller.PetController;
import org.pets.dao.*;
import org.pets.enums.PersonAttributes;
import org.pets.enums.PetAttributes;
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
                    PersonAttributes attributeToUpdate = view.choosePersonAttributeToUpdate();
                    if (attributeToUpdate == PersonAttributes.HOUSEHOLD) {
                        int idOfHouseholdToUpdate;
                        if (view.updateExisting()) {
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
                    break;

                case NEW_PET:
                    Pet newPet = view.getNewPetInfo();
                    petController.createPet(newPet);
                    break;

                case UPDATE_PET:
                    view.displayPetsAndOwner();
                    Pet petToUpdate = petController.getPet(view.chooseId(petController.getAllPetsIds()));
                    PetAttributes petAttributeToUpdate = view.choosePetAttributeToUpdate();
                    if (petAttributeToUpdate == PetAttributes.OWNER) {
                        int idOwnerToUpdate;
                        if (view.updateExisting()) {
                            view.displayPersonsAndHouseholds();
                            idOwnerToUpdate = view.chooseId(personController.getAllPersonsIds());

                        } else {
                            idOwnerToUpdate = personController.createPerson(view.getNewPersonInfo()).getId();
                        }
                        petToUpdate.setOwnerId(idOwnerToUpdate);
                    } else if (petAttributeToUpdate == PetAttributes.NAME) {
                        petToUpdate.setName(view.getNewName());
                    } else {
                        petToUpdate.setSpecies(view.getNewSpecies());
                    }
                    petController.updatePet(petToUpdate);
                    break;

                case DELETE_PET:
                    view.displayPetsAndOwner();
                    Pet petToDelete = petController.getPet(view.chooseId(petController.getAllPetsIds()));
                    petController.deletePet(petToDelete);
                    break;

                case EXIT:
                    exit = true;
            }
        }
    }

}
