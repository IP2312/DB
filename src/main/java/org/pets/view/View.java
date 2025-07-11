package org.pets.view;

import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);

    public void chooseAction(){
        System.out.println("Choose action");
        System.out.println("1. Add Household");
        String action = sc.nextLine();


    }
}
