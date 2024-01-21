package edu.mondragon.aiqua_server;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.TownRepository;
import edu.mondragon.aiqua_server.services.TownService;

class TownServiceTest {

    @MockBean
    private TownRepository townRepository;

    private TownService townService;

    @BeforeEach
    void setUp() {
        townRepository = mock(TownRepository.class);
        townService = new TownService(townRepository);
    }

    @Test
    void testList() {
        when(townRepository.findAll()).thenReturn(Collections.emptyList());
        List<Town> towns = townService.list();
        assertTrue(towns.isEmpty());
    }

    @Test
    void testReadTown_Existing() {
        Town existingTown = new Town();
        existingTown.setTownID(1);
        when(townRepository.findById(1)).thenReturn(Optional.of(existingTown));
        Town town = townService.readTown(1);
        assertNotNull(town);
        assertEquals(existingTown, town);
    }

    @Test
    void testReadTown_NonExisting() {
        when(townRepository.findById(1)).thenReturn(Optional.empty());
        Town town = townService.readTown(1);
        assertNull(town);
    }

    /* 
    @Test
    void testReadTownsByZone_WithTowns() {
        Zone zone = new Zone(1, "Urola Erdia");

        List<Town> towns = new ArrayList<>();
        towns.add(new Town(1, "Azkoitia", zone));
        towns.add(new Town(2, "Azpeitia", zone));

        when(townRepository.findByZone(zone)).thenReturn(towns);

        List<Town> resultTowns = townService.readTownsByZone(1);

        System.out.println("towns: " + towns);
        System.out.println("resultTowns: " + resultTowns);

        assertFalse(resultTowns.isEmpty());
        assertEquals(towns, resultTowns);
    }*/

    @Test
    void testReadTownsByZone_WithoutTowns() {
        Zone zone = new Zone(1, "Urola Erdia");

        when(townRepository.findByZone(zone)).thenReturn(Collections.emptyList());

        List<Town> resultTowns = townService.readTownsByZone(1);

        assertTrue(resultTowns.isEmpty());
    }

    @Test
    void testReadTownByDescription() {
        String description = "Azkoitia";
        when(townRepository.findByDescription(description)).thenReturn(new Town());

        Town town = townService.readTownByDescription(description);

        assertNotNull(town);
    }

    @Test
    void testCreateTown() {
        String description = "Azkoitia";

        townService.createTown(description);

        verify(townRepository, times(1)).save(any());
    }

    @Test
    void testUpdateTown() {
        Integer townID = 1;
        String description = "Azkoitia";
        Town existingTown = new Town();
        existingTown.setTownID(townID);
        when(townRepository.findById(townID)).thenReturn(Optional.of(existingTown));

        townService.updateTown(townID, description);

        assertEquals(description, existingTown.getDescription());
        verify(townRepository, times(1)).save(existingTown);
    }

    @Test
    void testDeleteTown() {
        Integer townID = 1;
        Town existingTown = new Town();
        existingTown.setTownID(townID);
        when(townRepository.findById(townID)).thenReturn(Optional.of(existingTown));

        townService.deleteTown(townID);

        verify(townRepository, times(1)).delete(existingTown);
    }
}
