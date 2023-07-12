package com.grupo4.APIAvesdoBrasil.controller;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.service.BirdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BirdsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BirdService birdService;

    @InjectMocks
    private BirdsController birdsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetBirds() throws Exception{
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
        Bird bird2 = new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird");
        List<Bird> birdsList = Arrays.asList(bird1, bird2);
        when(birdService.getAllBirds()).thenReturn(birdsList);


    }
}