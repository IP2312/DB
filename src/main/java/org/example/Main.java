package org.example;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


//        Connection con = null;
//        try {
//            con = Database.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (con != null){
//            System.out.println("Verbindung mit der Datenbank hergestellt!");
//        } else {
//            System.out.println("Verbindung mit der Datenbank konnte nicht hergestellt werden!");
//        }

/*
        PetDAO petDAO = new PetDAOImpl();
        Pet pet;
        try {
            pet = petDAO.get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(pet);


        pet = new Pet(0, "Maxxx", 6, "Male", 1);
        int result = 0;
        try {
            result = petDAO.insert(pet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);*/

//        PetDAO petDAO = new PetDAOImpl();
//        Pet pet = new Pet(5, "Maxxx", 6, "Male", 25);
//
//        try {
//            petDAO.update(pet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(pet);

        PetDAO petDAO = new PetDAOImpl();
        try {
            Pet pet = petDAO.get(4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}