package org.pets.model;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private int householdId;

    public Person(int id, String firstName, String lastName, int householdId) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        this.householdId = householdId;
    }


    public Person(String firstName, String lastName, int householdId) {
        setFirstName(firstName);
        setLastName(lastName);
        this.householdId = householdId;
    }

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getHouseholdId() {
        return householdId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", householdId='" + householdId + '\'' +
                '}';
    }
}



