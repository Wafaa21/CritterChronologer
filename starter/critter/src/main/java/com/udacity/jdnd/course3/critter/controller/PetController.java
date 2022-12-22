package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet=new Pet();
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setName(petDTO.getName());
        Long ownerId=petDTO.getOwnerId();
        pet=petService.savePetWithCustomer(pet,ownerId);
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.findById(petId).orElse(null));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAll();
        List<PetDTO> petDTOS;
        petDTOS=  pets.stream().map(x->convertPetToPetDTO(x)).collect(Collectors.toList());

        return petDTOS;   }



    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> pets = petService.findPetsByCustomerId(ownerId);
        List<PetDTO> petDTOs = null;

        if (pets != null) {
            petDTOs = pets.stream().map(x -> convertPetToPetDTO(x)).collect(Collectors.toList());
        }
        return petDTOs;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
//        PetDTO petDTO = new PetDTO();
//        BeanUtils.copyProperties(pet, petDTO);
//        return petDTO;
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        return petDTO;
    }
}
