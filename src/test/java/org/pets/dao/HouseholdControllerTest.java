package org.pets.dao;

import org.junit.jupiter.api.Test;
import org.pets.controller.HouseholdController;
import org.pets.model.Household;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HouseholdControllerTest {
    @Test
    public void createHouseholdTest() {
        HouseholdController householdController = new HouseholdController(new HouseholdDAOImpl());
        Household household = householdController.createHousehold("Badergasse 13");

        assertEquals("Badergasse 13", householdController.getHouseholdAddress(household.getId()));



    }
}
