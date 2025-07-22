package org.pets.model;

public class Household {
    private int id;
    private final String adresse;

    public Household(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }

    public Household(String adresse) {
        this.adresse = adresse;
    }


    public int getId() {
        return id;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
    }






    @Override
    public String toString() {
        return "household: " +
                "id=" + id +
                ", adresse='" + adresse ;
    }
}


