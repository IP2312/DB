package org.pets.controller;

import org.pets.dao.PersonDAO;
import org.pets.model.Person;

public class PersonController {
    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person createPerson(String firstName, String lastName){
        Person person = new Person(firstName, lastName);
    }

}
