package com.grupo4.APIAvesdoBrasil.controller;


import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BirdsController {
    @Autowired
    private BirdService birdService;

//    @PostMapping("/bird")
//    public ResponseEntity<Bird> addBird(@RequestBody Bird bird)
//


}
