package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.ResourceNotFoundException;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import com.grupo4.APIAvesdoBrasil.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdServiceImpl implements BirdService {

@Autowired
 private BirdsRepository birdsRepository;

    public BirdServiceImpl(BirdsRepository repository) {
    }

    @Override
    public List<Bird> findAll() {
        List<Bird> birds = birdsRepository.findAll();
        if (birds.isEmpty()){
            throw new EntityNotFoundException("No Birds here");

        }
            return birds;
        }



//    @Override
//    public Bird findById(int id) {
//        Optional<Bird> birdRetrieved = birdsRepository.findById((id));
//        if (birdRetrieved == null) {
//            new ResourceNotFoundException("No Bird Found");
//        }
//        return birdRetrieved.get();
//    }


    @Override
    public Bird findById(int id) {

        return birdsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id "+id +" not found " ));
    }

    @Override
    public Bird findByName(String commonName) {
        List<Bird> birds = birdsRepository.findAll();

        Optional<Bird> birdOptional = birds.stream()
                .filter(bird -> bird.getCommonName().equals(commonName))
                .findFirst();

        return birdOptional.orElseThrow(() -> new EntityNotFoundException("Bird not found with name: " + commonName));
    }

    @Override
    public Bird save(Bird bird) {

        return birdsRepository.save(bird);
    }

    @Override
    public Bird deleteById(int id) {
        return null;
    }


    // UPDATE COMMON NAME, DESCRIPTION, SCIENTIFIC NAME BY ID

    public Bird updateCommonName(String commonName, int id) {

        Bird birdToBeUpdated = findById(id);

        if (birdToBeUpdated != null) {
            birdToBeUpdated.setCommonName(commonName);
            return birdsRepository.save(birdToBeUpdated);
        }
        return null;
    }
    public Bird updateScientificName(String scientificName, int id) {

        Bird birdToBeUpdated = findById(id);

        if (birdToBeUpdated != null) {
            birdToBeUpdated.setScientificName(scientificName);
            return birdsRepository.save(birdToBeUpdated);
        }
        return null;
    }
    public Bird updateDescription(String description, int id) {

        Bird birdToBeUpdated =findById(id);

        if (birdToBeUpdated != null) {
            birdToBeUpdated.setDescription(description);
            return birdsRepository.save(birdToBeUpdated);
        }
        return null;
    }




}
