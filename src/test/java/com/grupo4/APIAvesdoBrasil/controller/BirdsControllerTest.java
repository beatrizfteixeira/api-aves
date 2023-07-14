package com.grupo4.APIAvesdoBrasil.controller;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import com.grupo4.APIAvesdoBrasil.service.BirdService2;
import com.grupo4.APIAvesdoBrasil.service.BirdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BirdsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BirdService2 birdService;

    @InjectMocks
    private BirdsController birdsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(birdsController).build();

    }


    @Test
    void testGetBirds() throws Exception{
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
        Bird bird2 = new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird");
        List<Bird> birdsList = Arrays.asList(bird1, bird2);
        when( birdService.findAll()).thenReturn(birdsList);

    }


    @Test
    void testSaveBird() throws Exception {
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
    when(birdService.save(bird1)).thenReturn(bird1);
    }

    @Test
    void testGetBirdById() throws Exception {
        int birdId = 1;
        Bird bird = new Bird(birdId, "pardal", "Passer domesticus", "Common found Bird");
        when(birdService.findById(birdId)).thenReturn(bird);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bird/{id}", birdId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(birdId))
                .andExpect(jsonPath("$.commonName").value("pardal"))
                .andExpect(jsonPath("$.scientificName").value("Passer domesticus"))
                .andExpect(jsonPath("$.description").value("Common found Bird"));


    }

    @Test
    void testDeleteBirdById() throws Exception {
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
        birdService.save(bird1);
        when(birdService.deleteById(1)).thenReturn(bird1);


        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bird/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("DELETED!"));

    }
}