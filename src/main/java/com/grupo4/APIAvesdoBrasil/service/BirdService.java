package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirdService {

    @Autowired
    private BirdsRepository birdsRepository;

    public Bird addNewBird(Bird bird) {
        return birdsRepository.save(bird);
    }
}
