package com.grupo4.APIAvesdoBrasil.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birds")
public class BirdsController {

    @GetMapping
    public void SaveBird(){

    }


    // insert a new bird to db
//    @PostMapping("/bird")
//    public List<Bird> addBird() {
//
//    }



}
