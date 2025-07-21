package org.pets.controller;

import org.pets.dao.PersonDAO;
import org.pets.model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonController {
    private final PersonDAO personDAO;
    private final HouseholdController householdController;

    public PersonController(PersonDAO personDAO, HouseholdController householdController) {
        this.personDAO = personDAO;
        this.householdController = householdController;
    }

    public Person createPerson(Person person) {
        try {
            int id = personDAO.insert(person);
            person.setId(id);
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPerson(int id) {
        try {
            return personDAO.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Person> getAllPersons() {
        try {
            return personDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> getAllPersonsIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Person person : getAllPersons()) {
            ids.add(person.getId());
        }
        return ids;
    }

    public ArrayList<HashMap<String, String>> getAllPersonsWithHouseholds() {
        ArrayList<Person> persons = getAllPersons();
        ArrayList<HashMap<String, String>> personsHouseholds = new ArrayList<>();
        for (Person person : persons) {
            HashMap<String, String> personHousehold = new HashMap<>();
            personHousehold.put("id", String.valueOf(person.getId()));
            personHousehold.put("first_name", person.getFirstName());
            personHousehold.put("last_name", person.getLastName());
            personHousehold.put("address", householdController.getHouseholdAddress(person.getHouseholdId()));
            personsHouseholds.add(personHousehold);
        }
        return personsHouseholds;
    }

    public void updatePerson(Person person) {
        try {
            personDAO.update(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(Person person) {
        try {
            personDAO.delete(person.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


