package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest
class BirdServiceTest {
    private MockMvc mockMvc;

    @InjectMocks
    BirdService birdService;

    @Mock
    BirdsRepository birdsRepository;

    @BeforeEach
    public void setup() {
        birdsRepository = Mockito.mock(BirdsRepository.class);
        birdService = new BirdServiceImpl(birdsRepository);
    }

//    fazer teste Delete byID
    @Test
    void testDeleteById() {
        birdsRepository.deleteById(1);
        verify(birdsRepository).deleteById(1);
    }

    @Test
    void findById() {
        Bird bird1 = new Bird(1, "Pardal", "Passer domesticus", "Common found Bird");

        Optional<Bird> birdRetrieved = mock(Optional.class);
//        when(birdsRepository.findById(1)).thenReturn(birdRetrieved);
        if (birdRetrieved.isPresent()) {
            Bird birdFound = birdRetrieved.get();
            birdFound.setId(1);
            assertNotNull(birdFound);
            assertEquals(1, birdFound.getId());
            assertEquals("Pardal", birdFound.getCommonName());
            assertEquals("Passer domesticus", birdFound.getScientificName());
            assertEquals("Common found Bird", birdFound.getDescription());
        }
    }
}
