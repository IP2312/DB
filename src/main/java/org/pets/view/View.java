package org.pets.view;

import org.pets.enums.Actions;
import org.pets.model.Household;
import org.pets.util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();

    public Actions chooseAction() {
        while (true) {
            System.out.println("Choose action");
            System.out.println("1. Add Household");
            System.out.println("2. Update Household");
            System.out.println("3. Delete Household");
            System.out.println("4. Add Person");
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

    public int chooseHouseholdId(ArrayList<Integer> possibleIds){
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

}
