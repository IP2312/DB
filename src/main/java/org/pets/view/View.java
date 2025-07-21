package org.pets.view;

import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.enums.Actions;
import org.pets.enums.PersonAttributes;
import org.pets.model.Household;
import org.pets.model.Person;
import org.pets.model.Pet;

import java.util.HashMap;
import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);
    HouseholdController householdController;
    PersonController personController;


    public View(HouseholdController householdController, PersonController personController) {
        this.householdController = householdController;
        this.personController = personController;
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

    public int chooseId() {
        int id = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Choose by id:");

            try {
                id = Integer.parseInt(sc.nextLine());
                validInput = householdController.getAllHouseholdIds().contains(id);

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
                chosenHousehold = chooseId();
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

    public PersonAttributes chooseItemToUpdate() {
        System.out.println("Choose item to update by Id:");
        for (PersonAttributes attribute : PersonAttributes.values()) {
            System.out.println("Id: " + attribute.getId() + "Update:" + attribute.name());
        }

        while (true) {
            String input = sc.nextLine().trim();
            try {
                return PersonAttributes.fromId(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean updateExistingHousehold() {
        System.out.println("Choose existing household(E) or create new one(N): ");
        String input = sc.nextLine().trim();
        while (true) {
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
                System.out.println("Choose household id:");
                displayPersonsAndHouseholds();
                chosenPerson= chooseId();
                validInput = true;

            } else if (input.equalsIgnoreCase("N")) {
                chosenPerson = personController.createPet(getNewPetInfo()).getId();
                validInput = true;
            }
        } while (!validInput);

        return new Pet(name, species, chosenPerson);
    }


}