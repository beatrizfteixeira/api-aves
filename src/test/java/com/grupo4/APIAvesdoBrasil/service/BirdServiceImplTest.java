package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.EntityNotFoundException;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import com.grupo4.APIAvesdoBrasil.service.BirdServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BirdServiceImplTest {

    @Mock
    BirdsRepository birdsRepository;

    @InjectMocks
    BirdServiceImpl birdService;



    @Test
    void save() {
        Bird bird1 = new Bird(1, "Pardal", "Passer domesticus", "Common found Bird");
        birdService.save(bird1);
        verify(birdsRepository).save(bird1);

    }

    @Test
    void findAlltest() {
//        Bird bird1 = new Bird(1, "pardal", "Passer domesticus", "Common found Bird");
//        Bird bird2 = new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird");
//
//        List<Bird> birdsList = Arrays.asList(bird1,bird2);
//
//        BirdsRepository repository = Mockito.mock(BirdsRepository.class);
//
//        when(repository.findAll()).thenReturn(birdsList);
//
//        BirdService birdService = new BirdServiceImpl(repository);
//
//        // Atualizar a configuração do serviço BirdServiceImpl com o mock do repositório
//        birdService.findAll();
//        // Act
//        List<Bird> result = birdService.findAll();
//
//        // Assert
//        Assertions.assertEquals(birdsList, result);
//
//        Mockito.verify(repository, .findAll();
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

    @Test
    void findByName() {
    }



    @Test
    void testDeleteById() {
        birdsRepository.deleteById(1);
        verify(birdsRepository).deleteById(1);
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