package com.grupo4.APIAvesdoBrasil.BirdControllerIntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo4.APIAvesdoBrasil.controller.BirdsController;
import com.grupo4.APIAvesdoBrasil.entity.Bird;

import com.grupo4.APIAvesdoBrasil.service.BirdService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(BirdsController.class)
@AutoConfigureRestDocs
class BirdsControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    BirdService birdService;

    @InjectMocks
    BirdsController birdsController;

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

        mockMvc.perform(MockMvcRequestBuilders.get("/api/birds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].commonName").value("pardal"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scientificName").value("Passer domesticus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Common found Bird"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].scientificName").value("Anas platyrhynchos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("Common livestock bird"));
        verify(birdService).findAll();

    }
    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSaveBird() throws Exception {
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
        when(birdService.save(bird1)).thenReturn(bird1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bird")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bird1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.commonName").value("pardal"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scientificName").value("Passer domesticus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Common found Bird"));

        verify(birdService, times(1)).save(bird1);
    }

    @Test
    void testGetBirdById() throws Exception {
        int birdId = 1;
        Bird bird = new Bird(birdId, "pardal", "Passer domesticus", "Common found Bird");
        when(birdService.findById(2)).thenReturn(bird);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bird/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(birdId))
                .andExpect(jsonPath("$.commonName").value("pardal"))
                .andExpect(jsonPath("$.scientificName").value("Passer domesticus"))
                .andExpect(jsonPath("$.description").value("Common found Bird"));
    }

    @Test
    void testGetBirdByName() throws Exception {
        // Create a mock bird
        Bird mockBird = new Bird();
        mockBird.setId(1);
        mockBird.setCommonName("pardal");
        mockBird.setScientificName("Passer domesticus");
        mockBird.setDescription("Common found Bird");

        when(birdService.findByName("pardal")).thenReturn(mockBird);

        mockMvc.perform(get("/api/bird/name/pardal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.commonName").value("pardal"))
                .andExpect(jsonPath("$.scientificName").value("Passer domesticus"))
                .andExpect(jsonPath("$.description").value("Common found Bird"));

        verify(birdService, times(1)).findByName("pardal");
    }

    @Test
    void testDeleteBirdById() throws Exception {
        // Create a mock bird
        Bird mockBird = new Bird();
        mockBird.setId(1);
        mockBird.setCommonName("pardal");
        mockBird.setScientificName("Passer domesticus");
        mockBird.setDescription("Common found Bird");

        when(birdService.deleteById(1)).thenReturn(mockBird);

        mockMvc.perform(delete("/api/bird/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("BIRD DELETED!"));

        verify(birdService, times(1)).deleteById(1);
    }

    @Test
    void testUpdateBirdById() throws Exception {
        Bird mockBird = new Bird();
        mockBird.setId(1);
        mockBird.setCommonName("pardal");
        mockBird.setScientificName("Passer domesticus");
        mockBird.setDescription("Common found Bird");

        when(birdService.findById(1)).thenReturn(mockBird);

        String requestBody = "{ \"commonName\": \"Pato\", \"scientificName\": \"Anas platyrhynchos\", \"description\": \"Common livestock bird\" }";

        mockMvc.perform(put("/api/bird/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.commonName").value("pardal"))
                .andExpect(jsonPath("$.scientificName").value("Passer domesticus"))
                .andExpect(jsonPath("$.description").value("Common found Bird"));

        verify(birdService, times(1)).updateCommonName("Pato", 1);
        verify(birdService, times(1)).updateScientificName("Anas platyrhynchos", 1);
        verify(birdService, times(1)).updateDescription("Common livestock bird", 1);
    }
}