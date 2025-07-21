package org.pets.view;

import org.pets.enums.Actions;
import org.pets.enums.PersonAttributes;
import org.pets.model.Household;
import org.pets.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);

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

    public void displayHouseholds(ArrayList<Household> households) {
        System.out.println("Households: ");
        for (Household household : households) {
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


    public Person getNewPersonInfo(ArrayList<Household> households, ArrayList<Integer> possibleIds) {
        System.out.println("Enter first name:");
        String firstName = sc.nextLine().trim();
        System.out.println("Enter last name:");
        String lastName = sc.nextLine().trim();
        displayHouseholds(households);
        int chosenHousehold = chooseId(possibleIds);
        return new Person(firstName, lastName, chosenHousehold);
    }


    public void displayPersonsAndHouseholds(ArrayList<HashMap<String, String>> personsHouseholds) {
        System.out.println("Persons and Households: ");
        for (HashMap<String, String> personHousehold : personsHouseholds) {
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
}


