package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;

import java.util.List;

public interface BirdService2 {

    public List<Bird> findAll();

    public Bird findById(int id);

    public Bird save(Bird bird);

    public Bird deleteById(int id);

}
