package org.pets.util;

import java.util.ArrayList;

public class InputValidator {

public boolean validateId(int id, ArrayList<Integer> possibleIds){
    return possibleIds.contains(id);
}

}
