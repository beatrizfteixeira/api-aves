package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import com.grupo4.APIAvesdoBrasil.service.BirdServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BirdServiceImplTest {



    @Mock
    private BirdsRepository birdsRepository;
    @InjectMocks
    private BirdServiceImpl birdService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void findById() {


        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");

       when(birdsRepository.findById(1)).thenReturn(Optional.of(bird1));

       Bird result = birdService.findById(1);


        assertNotNull(result);
        assertEquals("pardal", result.getCommonName());
        assertEquals("Passer domesticus", result.getScientificName());
        assertEquals("Common found Bird", result.getDescription());
        verify(birdsRepository, times(1)).findById(1);
    }



    @Test
    void findAlltest() {
        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");


        BirdsRepository repository = Mockito.mock(BirdsRepository.class);

        Bird bird2 = new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird");
        List<Bird> birdsList = Arrays.asList(bird1);


        when(repository.findAll()).thenReturn(birdsList);

        BirdService birdService = new BirdServiceImpl(repository);

        // Atualizar a configuração do serviço BirdServiceImpl com o mock do repositório
        birdService.findAll();

        // Act
        List<Bird> result = birdService.findAll();

        // Assert
        Assertions.assertEquals(birdsList, result);




    }



    @Test
    void findByName() {
    }

    @Test
    void save() {


    }

    @Test
    void testDeleteById() {
    }

    @Test
    void updateCommonName() {
    }

    @Test
    void updateScientificName() {
    }

    @Test
    void updateDescription() {
    }
}

//        Mockito.when(birdsRepository.findById(1).thenReturn();
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,() -> birdService.save(bird1));
//        assertTrue(exception.getMessage().contains("Pardal Cadastrado"));