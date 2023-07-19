package com.grupo4.APIAvesdoBrasil.service;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.exception.BirdDeleteIdInvalidException;

import com.grupo4.APIAvesdoBrasil.exception.BirdNotFoundException;
import com.grupo4.APIAvesdoBrasil.exception.BirdSaveException;
import com.grupo4.APIAvesdoBrasil.repository.BirdsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BirdServiceImplTest {

    @Mock
    BirdsRepository birdsRepository;

    @InjectMocks
    BirdServiceImpl birdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Bird> mockBirds = new ArrayList<>();
        mockBirds.add(new Bird(1, "Pardal", "Passer domesticus", "Common found Bird"));
        mockBirds.add(new Bird(2, "Pato", "Anas platyrhynchos", "Common livestock bird"));

        when(birdsRepository.findAll()).thenReturn(mockBirds);

        List<Bird> result = birdService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        int birdId = 1;
        Bird mockBird = new Bird(birdId, "Pardal", "Passer domesticus", "Common found Bird");

        when(birdsRepository.findById(birdId)).thenReturn(Optional.of(mockBird));

        Bird result = birdService.findById(birdId);

        assertNotNull(result);
        assertEquals(mockBird.getCommonName(), result.getCommonName());
        assertEquals(mockBird.getScientificName(), result.getScientificName());
        assertEquals(mockBird.getDescription(), result.getDescription());

    }

    @Test
    void testFindByName_WhenBirdExists() {
        String commonName = "Pardal";
        Bird mockBird = new Bird(1, commonName, "Passer domesticus", "Common found Bird");

        List<Bird> mockBirds = new ArrayList<>();
        mockBirds.add(mockBird);
        when(birdsRepository.findAll()).thenReturn(mockBirds);

        Bird result = birdService.findByName(commonName);

        assertNotNull(result);
        assertEquals(mockBird.getCommonName(), result.getCommonName());
        assertEquals(mockBird.getScientificName(), result.getScientificName());
        assertEquals(mockBird.getDescription(), result.getDescription());
    }

    @Test
    void testFindByName_WhenBirdDoesNotExist() {
        String commonName = "Pombo";

        when(birdsRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(BirdNotFoundException.class, () -> birdService.findByName(commonName));
    }

    @Test
    void testSave_ValidBird() {
        Bird birdToSave = new Bird(1, "Pardal", "Passer domesticus", "Common found Bird");

        when(birdsRepository.save(any())).thenReturn(birdToSave);

        Bird result = birdService.save(birdToSave);

        assertNotNull(result);
        assertEquals(birdToSave.getCommonName(), result.getCommonName());
        assertEquals(birdToSave.getScientificName(), result.getScientificName());
        assertEquals(birdToSave.getDescription(), result.getDescription());
    }

    @Test
    void testSave_InvalidBird() {
        Bird invalidBird = new Bird(1, null, null, null);

        assertThrows(BirdSaveException.class, () -> birdService.save(invalidBird));
    }

    @Test
    void testDeleteById_WhenValidId() {
        int birdId = 1;
        Bird mockBird = new Bird(birdId, "Pardal", "Passer domesticus", "Common found Bird");

        when(birdsRepository.findById(birdId)).thenReturn(Optional.of(mockBird));

        Bird result = birdService.deleteById(birdId);

        assertNull(result);
        verify(birdsRepository, times(1)).deleteById(birdId);
    }

    @Test
    void testDeleteById_WhenInvalidId() {
        int invalidId = -1;

        assertThrows(BirdDeleteIdInvalidException.class, () -> birdService.deleteById(invalidId));
    }

    @Test
    public void testUpdateCommonNameSuccess() {
        int birdId = 1;
        String commonName = "Updated Common Name";

        // Mock the behavior of birdsRepository.findById()
        Bird existingBird = new Bird();
        existingBird.setId(birdId);
        when(birdsRepository.findById(birdId)).thenReturn(Optional.of(existingBird));

        // Mock the behavior of birdsRepository.save()
        when(birdsRepository.save(existingBird)).thenReturn(existingBird);

        Bird updatedBird = birdService.updateCommonName(commonName, birdId);

        Assertions.assertEquals(commonName, updatedBird.getCommonName());
        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, times(1)).save(existingBird);
    }

    @Test
    public void testUpdateCommonNameBirdNotFound() {
        int birdId = 1;
        String commonName = "Updated Common Name";

        // Mock the behavior of birdsRepository.findById() when bird is not found
        when(birdsRepository.findById(birdId)).thenReturn(Optional.empty());

        // The method should throw BirdUpdateException when the bird is not found
        Assertions.assertThrows(BirdNotFoundException.class,
                () -> birdService.updateCommonName(commonName, birdId));

        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, never()).save(any());
    }

    @Test
    public void testUpdateScientificNameSuccess() {
        int birdId = 1;
        String scientificName = "Updated Scientific Name";

        // Mock the behavior of birdsRepository.findById()
        Bird existingBird = new Bird();
        existingBird.setId(birdId);
        when(birdsRepository.findById(birdId)).thenReturn(Optional.of(existingBird));

        // Mock the behavior of birdsRepository.save()
        when(birdsRepository.save(existingBird)).thenReturn(existingBird);

        Bird updatedBird = birdService.updateScientificName(scientificName, birdId);

        Assertions.assertEquals(scientificName, updatedBird.getScientificName());
        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, times(1)).save(existingBird);
    }

    @Test
    public void testUpdateScientificNameBirdNotFound() {
        int birdId = 1;
        String scientificName = "Updated Scientific Name";

        // Mock the behavior of birdsRepository.findById() when bird is not found
        when(birdsRepository.findById(birdId)).thenReturn(Optional.empty());

        // The method should throw BirdUpdateException when the bird is not found
        Assertions.assertThrows(BirdNotFoundException.class,
                () -> birdService.updateScientificName(scientificName, birdId));

        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, never()).save(any());
    }

    @Test
    public void testUpdateDescriptionSuccess() {
        int birdId = 1;
        String description = "Updated Description";

        // Mock the behavior of birdsRepository.findById()
        Bird existingBird = new Bird();
        existingBird.setId(birdId);
        when(birdsRepository.findById(birdId)).thenReturn(Optional.of(existingBird));

        // Mock the behavior of birdsRepository.save()
        when(birdsRepository.save(existingBird)).thenReturn(existingBird);

        Bird updatedBird = birdService.updateDescription(description, birdId);

        Assertions.assertEquals(description, updatedBird.getDescription());
        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, times(1)).save(existingBird);
    }

    @Test
    public void testUpdateDescriptionBirdNotFound() {
        int birdId = 1;
        String description = "Updated Description";

        // Mock the behavior of birdsRepository.findById() when bird is not found
        when(birdsRepository.findById(birdId)).thenReturn(Optional.empty());

        // The method should throw BirdUpdateException when the bird is not found
        Assertions.assertThrows(BirdNotFoundException.class,
                () -> birdService.updateDescription(description, birdId));

        verify(birdsRepository, times(1)).findById(birdId);
        verify(birdsRepository, never()).save(any());
    }
}