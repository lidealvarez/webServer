package edu.mondragon.aiqua_server;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.repositories.ReductorRepository;
import edu.mondragon.aiqua_server.services.ReductorService;

class ReductorServiceTest {

    @MockBean
    private ReductorRepository reductorRepository;

    private ReductorService reductorService;

    @BeforeEach
    void setUp() {
        reductorRepository = mock(ReductorRepository.class);
        reductorService = new ReductorService(reductorRepository);
    }

    @Test
    void testList() {
        when(reductorRepository.findAll()).thenReturn(Collections.emptyList());
        List<Reductor> reductors = reductorService.list();
        assertTrue(reductors.isEmpty());
    }

    @Test
    void testReadReductor_Existing() {
        Reductor existingReductor = new Reductor();
        existingReductor.setReductorID(1);
        when(reductorRepository.findById(1)).thenReturn(Optional.of(existingReductor));

        Reductor reductor = reductorService.readReductor(1);

        assertNotNull(reductor);
        assertEquals(existingReductor, reductor);
    }

    @Test
    void testReadReductor_NonExisting() {
        when(reductorRepository.findById(1)).thenReturn(Optional.empty());

        Reductor reductor = reductorService.readReductor(1);

        assertNull(reductor);
    }

    @Test
    void testReadReductorsByTown_WithReductors() {
        Town town = new Town();
        town.setTownID(1);

        List<Reductor> reductors = new ArrayList<>();
        reductors.add(new Reductor(1,"Berazubi", 4.3, town));

        when(reductorRepository.findByTown(town)).thenReturn(reductors);

        List<Reductor> resultReductors = reductorService.readReductorsByTown(town);

        assertFalse(resultReductors.isEmpty());
        assertEquals(reductors, resultReductors);
    }

    @Test
    void testReadReductorsByTown_WithoutReductors() {
        Town town = new Town();
        town.setTownID(1);

        when(reductorRepository.findByTown(town)).thenReturn(Collections.emptyList());

        List<Reductor> resultReductors = reductorService.readReductorsByTown(town);

        assertTrue(resultReductors.isEmpty());
    }

    @Test
    void testDeleteReductor() {
        Integer reductorID = 1;
        Reductor existingReductor = new Reductor();
        existingReductor.setReductorID(reductorID);
        when(reductorRepository.findById(reductorID)).thenReturn(Optional.of(existingReductor));

        reductorService.deleteReductor(reductorID);

        verify(reductorRepository, times(1)).delete(existingReductor);
    }
}
