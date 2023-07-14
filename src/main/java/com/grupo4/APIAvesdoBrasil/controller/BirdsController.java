package com.grupo4.APIAvesdoBrasil.controller;


import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import com.grupo4.APIAvesdoBrasil.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BirdsController {
    @Autowired
    private BirdService birdService;

    // POST
    // Insert a new bird to db http://localhost:8080/api/bird
    @PostMapping("/bird")
    public ResponseEntity<Bird> addBird(@RequestBody Bird bird) {
        Bird birdCreated = birdService.addNewBird(bird);
        return new ResponseEntity<>(bird, HttpStatus.CREATED);
    }
    // GET all birds from db http://localhost:8080/api/bird
    //changed endpoint /bird to /birds as springboot conventions
    @GetMapping("/birds")
    public ResponseEntity<List<Bird>> getBirds() {
        List<Bird> birdsList = birdService.getAllBirds();
        return ResponseEntity.ok(birdsList);
    }
    // GET
    // Get a bird by ID from db http://localhost:8080/api/bird/{id}
    @GetMapping("/bird/{id}")
    public ResponseEntity<Bird> getBirdById(@PathVariable("id") Integer id) {
        Bird bird = birdService.getBirdById(id);
        return ResponseEntity.ok(bird);
    }

    // UPDATE a bird by ID from db http://localhost:8080/api/bird/{id}/update
    @PutMapping("/bird/{id}/update")
    public ResponseEntity<Bird> updateBirdbyId(@PathVariable("id") Integer id, @RequestBody Bird bird){
        Bird birdFound = birdService.getBirdById(id);

        if (bird.getCommonName() != null) {
            birdService.updateCommonName(bird.getCommonName(), id);
        }
        if (bird.getScientificName() != null) {
            birdService.updateScientificName(bird.getScientificName(), id);
        }
        if (bird.getDescription() != null) {
            birdService.updateDescription(bird.getDescription(), id);
        }
        return ResponseEntity.ok(birdService.getBirdById(id));
    }

    // DELETE a bird by ID from db http://localhost:8080/api/bird/{id}
    @DeleteMapping("/bird/{id}")
    public ResponseEntity<String> deleteBirdById(@PathVariable("id") Integer id) {
        Bird bird = birdService.deleteById(id);
        if (bird != null) {
            return ResponseEntity.ok("DELETED!");
        }
        return ResponseEntity.ok("ERROR");
    }

}
