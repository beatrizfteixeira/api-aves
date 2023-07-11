package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;

public class BirdService {

    private BirdsRepository birdsRepository;

    public Bird addNewBird(Bird bird) {
        return birdsRepository.save(bird);
    }
}
