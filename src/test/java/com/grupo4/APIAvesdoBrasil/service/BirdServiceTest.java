package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BirdServiceTest {
    private MockMvc mockMvc;

    @Mock
    private BirdService2 birdService;

    @Test
    void getBirdById() {
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
        Bird bird2 = new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird");
        List<Bird> birdsList = Arrays.asList(bird1, bird2);

        when(birdsList.get(1)).thenReturn(bird1);

    }
}
