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
}
