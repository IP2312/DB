package org.pets.view;

import org.pets.enums.Actions;
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
            System.out.println("1. Add Household");
            System.out.println("2. Update Household");
            System.out.println("3. Delete Household");
            System.out.println("4. Add Person");
            System.out.println("5. Update Person");
            System.out.println("0. Exit");
            String input = sc.nextLine().trim();


            try {
                return Actions.fromCode(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getAddress(){
        System.out.println("Enter the address of the household:");
        return sc.nextLine().trim();
    }

    public void displayHouseholds(ArrayList<Household> households){
        System.out.println("Households: ");
        for (Household household : households) {
            System.out.println(household);
        }
    }

    public int chooseId(ArrayList<Integer> possibleIds){
        int id = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.println("Choose household by id:");

            try{
                id = Integer.parseInt(sc.nextLine());
                validInput = possibleIds.contains(id);

            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
        return id;
    }



    public Person getNewPersonInfo(ArrayList<Household> households, ArrayList<Integer> possibleIds){
        System.out.println("Enter first name:");
        String firstName = sc.nextLine().trim();
        System.out.println("Enter last name:");
        String lastName = sc.nextLine().trim();
        displayHouseholds(households);
        int chosenHousehold = chooseId(possibleIds);
        return new Person(firstName, lastName, chosenHousehold);
    }


//TODO
    public void displayPersonsAndHouseholds(ArrayList<HashMap<String, String>> personsHouseholds){
        System.out.println("Persons and Households: ");
        for (HashMap<String, String> personHousehold : personsHouseholds) {
            System.out.println(personHousehold);
        }
    }


}
