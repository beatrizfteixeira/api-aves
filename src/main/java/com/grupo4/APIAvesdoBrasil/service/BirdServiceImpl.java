package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdServiceImpl implements BirdService{

    private BirdsRepository birdsRepository;
    @Autowired
    public BirdServiceImpl(BirdsRepository theBirdsRepository){
        birdsRepository = theBirdsRepository;
    }
    @Override
    public List<Bird> findAll() {


        return birdsRepository.findAll();
    }

    @Override
    public Bird findById(int id) {
        Optional<Bird> result = birdsRepository.findById(id);
        Bird bird = null;

        if (result.isPresent()){
            bird = result.get();
        } else {
            throw new RuntimeException("Did not find the bird you are looking for - "+id);
        }

        return bird;
    }

    @Override
    public void save(Bird bird) {
        birdsRepository.save(bird);

    }

    @Override
    public void deleteById(int id) {
        birdsRepository.deleteById(id);
    }
}
