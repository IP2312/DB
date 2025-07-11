package org.pets.model;

public class Household {
    private int id;
    private String adresse;

    public Household(int id, String adresse) {
        this.id = id;
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


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }





    @Override
    public String toString() {
        return "household{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}


