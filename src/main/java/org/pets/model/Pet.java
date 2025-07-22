package org.pets.model;

public class Pet {
    private int id;
    private String name;
    private String species;
    private int ownerId;

    public Pet(int id, String name, String species, int ownerId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.ownerId = ownerId;
    }

    public Pet(String name, String species, int ownerId) {
        this.name = name;
        this.species = species;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
