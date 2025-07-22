package org.pets.controller;

import org.pets.dao.PetDAO;
import org.pets.model.Pet;

import java.util.ArrayList;
import java.util.HashMap;

public class PetController {
    private final PetDAO petDAO;
    private final PersonController personController;


    public PetController(PetDAO petDAO, PersonController personController) {
        this.petDAO = petDAO;
        this.personController = personController;
    }


    public Pet createPet(Pet pet){
        try{
            int id = petDAO.insert(pet);
            pet.setId(id);
            return pet;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Pet getPet(int id){
        try{
            return petDAO.get(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Pet> getAllPets(){
        try{
            return petDAO.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> getAllPetsIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Pet pet : getAllPets()) {
            ids.add(pet.getId());
        }
        return ids;
    }
   public ArrayList<HashMap<String, String>> getAllPetsWithOwners(){
        ArrayList<HashMap<String, String>> petsWithOwners = new ArrayList<>();
        for (Pet pet : getAllPets()) {
            HashMap<String, String> petWithOwner = new HashMap<>();
            petWithOwner.put("id", String.valueOf(pet.getId()));
            petWithOwner.put("name", pet.getName());
            petWithOwner.put("species", pet.getSpecies());
            petWithOwner.put("owner", personController.getPerson(pet.getOwnerId()).getFirstName());
            petsWithOwners.add(petWithOwner);
        }
        return petsWithOwners;
   }

   public void updatePet(Pet pet){
        try{
            petDAO.update(pet);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
   }

   public void deletePet(Pet pet){
        try{
            petDAO.delete(pet.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
   }

}
