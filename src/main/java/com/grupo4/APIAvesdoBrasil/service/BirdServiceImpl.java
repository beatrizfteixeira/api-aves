package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.GlobalExceptionHandler;
import com.grupo4.APIAvesdoBrasil.exception.ResourceNotFoundException;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdServiceImpl implements BirdService2 {

@Autowired
 private BirdsRepository birdsRepository;
    @Override
    public List<Bird> findAll() {
        List<Bird> birds = birdsRepository.findAll();
        if (birds.isEmpty()){
            throw new ResourceNotFoundException("No Birds here");

        }
            return birds;
        }


    @Override
    public Bird findById(int id) {
        Optional<Bird> birdRetrieved = birdsRepository.findById((id));
        if (birdRetrieved == null) {
            new ResourceNotFoundException("No Bird Found");
        }
        return birdRetrieved.get();
    }
    @Override
    public Bird save(Bird bird) {

        return birdsRepository.save(bird);
    }

    @Override
    public Bird deleteById(int id) {
        return null;
    }
}
