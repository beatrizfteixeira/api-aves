package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.GlobalExceptionHandler;
import com.grupo4.APIAvesdoBrasil.exception.ResourceNotFoundException;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BirdService {
    @Autowired
    private BirdsRepository birdsRepository;

    // POST
    public Bird addNewBird(Bird bird) {

        return birdsRepository.save(bird);
    }

    // GET BY ID
    public Bird getBirdById(Integer id) {
        Optional<Bird> birdRetrieved = birdsRepository.findById((id));
        if (birdRetrieved == null) {
            new GlobalExceptionHandler();
        }
            return birdRetrieved.get();
    }

        // GET ALL
    public List<Bird> getAllBirds()
    {
        return birdsRepository.findAll();
    }


//    // DELETE BY ID
    public Bird deleteById(Integer id) {
        Bird birdToBeDeleted = getBirdById(id);
        if (birdToBeDeleted != null) {
            birdsRepository.deleteById(id);
            return birdToBeDeleted;
        }
        return null;
    }
//
//    // UPDATE COMMON NAME, DESCRIPTION, SCIENTIFIC NAME BY ID
//    public Bird updateCommonName(String commonName, int id) {
//
//        Bird birdToBeUpdated = getBirdById(id);
//
//        if (birdToBeUpdated != null) {
//            birdToBeUpdated.setCommonName(commonName);
//            return birdsRepository.save(birdToBeUpdated);
//        }
//        return null;
//    }
//    public Bird updateScientificName(String scientificName, int id) {
//
//        Bird birdToBeUpdated = getBirdById(id);
//
//        if (birdToBeUpdated != null) {
//            birdToBeUpdated.setScientificName(scientificName);
//            return birdsRepository.save(birdToBeUpdated);
//        }
//        return null;
//    }
//    public Bird updateDescription(String description, int id) {
//
//        Bird birdToBeUpdated = getBirdById(id);
//
//        if (birdToBeUpdated != null) {
//            birdToBeUpdated.setDescription(description);
//            return birdsRepository.save(birdToBeUpdated);
//        }
//        return null;
//    }

    }

