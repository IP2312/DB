package org.example;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Pet pet = new Pet(1, "Coco",1,"female", 6);
        System.out.println(pet);


        Connection con = null;
        try {
            con = Database.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (con != null){
            System.out.println("Verbindung mit der Datenbank hergestellt!");
        } else {
            System.out.println("Verbindung mit der Datenbank konnte nicht hergestellt werden!");
        }

    }
}