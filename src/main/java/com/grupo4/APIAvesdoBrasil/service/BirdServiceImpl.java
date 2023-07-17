package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.*;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdServiceImpl implements BirdService {

@Autowired
 private BirdsRepository birdsRepository;

    public BirdServiceImpl(BirdsRepository repository) {
        this.birdsRepository = repository;
    }

    @Override
    public List<Bird> findAll() {
        List<Bird> birds = birdsRepository.findAll();
        if (birds.isEmpty()){
            throw new BirdNotFoundException("No Birds here");
        }
            return birds;
        }

    @Override
    public Bird findById(int id) {

//        return birdsRepository.getReferenceById(id);

        return birdsRepository.findById(id).orElseThrow(
                () -> new BirdNotFoundException("Id "+id +" not found " ));
    }

    @Override
    public Bird findByName(String commonName) {
        List<Bird> birds = birdsRepository.findAll();

        Optional<Bird> birdOptional = birds.stream()
                .filter(bird -> bird.getCommonName().equals(commonName))
                .findFirst();

        return birdOptional.orElseThrow(() -> new BirdNotFoundException("Bird not found with name: " + commonName));
    }

    @Override
    public Bird save(Bird bird) {
        if (bird.getCommonName() == null || bird.getScientificName() == null){
            throw new BirdSaveException("Provide a valid information");
        }
        try {
            return birdsRepository.save(bird);
        } catch (Exception e) {
            throw new BirdSaveException("Error while saving the bird: " + e.getMessage());
        }
    }

    @Override
    public Bird deleteById(int id) {
        if (id <= 0) {
            throw new BirdDeleteIdInvalidException("Invalid bird ID. Bird ID must be greater than 0.");
        }

        Bird birdToDelete = birdsRepository.findById(id).orElse(null);

        if (birdToDelete == null) {
            throw new BirdDeleteNotFoundException("Bird with ID " + id + " not found. Deletion failed.");
        }

        try {
            birdsRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new BirdDeleteNotFoundException("Error while deleting the bird: " + e.getMessage());
        }
    }

    // UPDATE COMMON NAME, DESCRIPTION, SCIENTIFIC NAME BY ID
    @Override
    public Bird updateCommonName(String commonName, int id) {
        Bird birdToBeUpdated = findById(id);
        if (birdToBeUpdated != null) {
            birdToBeUpdated.setCommonName(commonName);
            return birdsRepository.save(birdToBeUpdated);
        } else {
            throw new BirdUpdateException("Bird with ID " + id + " not found for common name update.");
        }
    }

    @Override
    public Bird updateScientificName(String scientificName, int id) {
        Bird birdToBeUpdated = findById(id);
        if (birdToBeUpdated != null) {
            birdToBeUpdated.setScientificName(scientificName);
            return birdsRepository.save(birdToBeUpdated);
        } else {
            throw new BirdUpdateException("Bird with ID " + id + " not found for scientific name update.");
        }
    }

    @Override
    public Bird updateDescription(String description, int id) {
        Bird birdToBeUpdated = findById(id);
        if (birdToBeUpdated != null) {
            birdToBeUpdated.setDescription(description);
            return birdsRepository.save(birdToBeUpdated);
        } else {
            throw new BirdUpdateException("Bird with ID " + id + " not found for description update.");
        }
    }
}
