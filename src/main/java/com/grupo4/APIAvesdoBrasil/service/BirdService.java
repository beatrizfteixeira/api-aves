package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;

import java.util.List;

public interface BirdService {

    List<Bird> findAll();

    Bird findById(int id);

    void save (Bird bird);

    void deleteById(int id);

}
