package org.pets.dao;

import org.junit.jupiter.api.Test;
import org.pets.controller.HouseholdController;
import org.pets.controller.PersonController;
import org.pets.model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonControllerTest {
    @Test
    public void createPersonAndGetPersonTest(){
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        PersonController personController = new PersonController(new PersonDAOImpl(),householdController);
        Person testPerson = personController.createPerson(new Person("Iason", "Pifeas", householdController.getAllHouseholds().getFirst().getId()));
        assertEquals(testPerson.getFirstName(), personController.getPerson(testPerson.getId()).getFirstName());
        assertEquals(testPerson.getLastName(), personController.getPerson(testPerson.getId()).getLastName());
        assertEquals(testPerson.getHouseholdId(), personController.getPerson(testPerson.getId()).getHouseholdId());
    }

}
