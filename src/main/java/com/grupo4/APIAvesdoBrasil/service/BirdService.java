package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;

import java.util.List;

public interface BirdService {

    public List<Bird> findAll();

    public Bird findById(int id);

    public Bird save(Bird bird);

    public Bird deleteById(int id);

    public Bird findByName(String commonName);

    public Bird updateCommonName(String commonName, int id);

    public Bird updateScientificName(String scientificName, int id);

    public Bird updateDescription(String description, int id);
}
