package org.pets.view;

import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.controller.PetController;
import org.pets.enums.Actions;
import org.pets.enums.PersonAttributes;
import org.pets.enums.PetAttributes;
import org.pets.model.Household;
import org.pets.model.Person;
import org.pets.model.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);
    HouseholdController householdController;
    PersonController personController;
    PetController petController;


    public View(HouseholdController householdController, PersonController personController, PetController petController) {
        this.householdController = householdController;
        this.personController = personController;
        this.petController = petController;
    }

    public Actions chooseAction() {
        while (true) {
            System.out.println("Choose action");

            for (Actions action : Actions.values()) {
                System.out.println(action.getCode() + ". " + action.name());
            }
            String input = sc.nextLine().trim();

            try {
                return Actions.fromCode(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getAddress() {
        System.out.println("Enter the address of the household:");
        return sc.nextLine().trim();
    }

    public void displayHouseholds() {
        System.out.println("Households: ");
        for (Household household : householdController.getAllHouseholds()) {
            System.out.println(household);
        }
    }

    public int chooseId(ArrayList<Integer> possibleIds) {
        int id = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Choose by id:");

            try {
                id = Integer.parseInt(sc.nextLine());
                validInput = possibleIds.contains(id);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        return id;
    }


    public Person getNewPersonInfo() {
        System.out.println("Enter first name:");
        String firstName = sc.nextLine().trim();
        System.out.println("Enter last name:");
        String lastName = sc.nextLine().trim();
        System.out.println("Choose existing household by id (E) or create new one (N):");
        String input = sc.nextLine().trim();
        boolean validInput = false;
        int chosenHousehold = 0;
        do {

            if (input.equalsIgnoreCase("E")) {
                System.out.println("Choose household id:");
                displayHouseholds();
                chosenHousehold = chooseId(householdController.getAllHouseholdIds());
                validInput = true;

            } else if (input.equalsIgnoreCase("N")) {
                chosenHousehold = householdController.createHousehold(getAddress()).getId();
                validInput = true;
            }
        } while (!validInput);

        return new Person(firstName, lastName, chosenHousehold);
    }


    public void displayPersonsAndHouseholds() {
        System.out.println("Persons and Households: ");
        for (HashMap<String, String> personHousehold : personController.getAllPersonsWithHouseholds()) {
            System.out.println(personHousehold);
        }
    }

    public PersonAttributes choosePersonAttributeToUpdate() {
        System.out.println("Choose attribute to update by Id:");
        for (PersonAttributes attribute : PersonAttributes.values()) {
            System.out.println("Id: " + attribute.getCode() + "Update:" + attribute.name());
        }

        while (true) {
            String input = sc.nextLine().trim();
            try {
                return PersonAttributes.fromCode(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public PetAttributes choosePetAttributeToUpdate() {
        System.out.println("Choose attribute to update by Id:");
        for (PetAttributes attribute : PetAttributes.values()) {
            System.out.println("Id: " + attribute.getCode() + "Update:" + attribute.name());
        }
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return PetAttributes.fromCode(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean updateExisting() {
        System.out.println("Choose existing(E) or create new one(N): ");

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("E")) {
                System.out.println("Enter household id:");
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            }
        }
    }


    public String getNewName() {
        System.out.println("Enter new name:");
        return sc.nextLine().trim();
    }

    public String getNewSpecies(){
        System.out.println("Enter new species");
        return sc.nextLine().trim();
    }

    public Pet getNewPetInfo() {
        System.out.println("Enter name:");
        String name = sc.nextLine().trim();
        System.out.println("Enter species:");
        String species = sc.nextLine().trim();
        System.out.println("Choose existing owner by id (E) or create new one (N):");
        String input = sc.nextLine().trim();
        boolean validInput = false;
        int chosenPerson = 0;
        do {
            if (input.equalsIgnoreCase("E")) {
                System.out.println("Choose id:");
                displayPersonsAndHouseholds();
                chosenPerson = chooseId(personController.getAllPersonsIds());
                validInput = true;

            } else if (input.equalsIgnoreCase("N")) {
                chosenPerson = personController.createPerson(getNewPersonInfo()).getId();
                validInput = true;
            }
        } while (!validInput);
        return new Pet(name, species, chosenPerson);
    }

    public void displayPetsAndOwner() {
        System.out.println("Pets and owners: ");
        for (HashMap<String, String> petAndOwner : petController.getAllPetsWithOwners()) {
            System.out.println(petAndOwner);
        }
    }


}