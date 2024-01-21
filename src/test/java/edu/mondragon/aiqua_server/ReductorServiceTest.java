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

import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.ZoneRepository;
import edu.mondragon.aiqua_server.services.ZoneService;

class ReductorServiceTest {

    @MockBean
    private ZoneRepository zoneRepository;

    private ZoneService zoneService;

    @BeforeEach
    void setUp() {
        zoneRepository = mock(ZoneRepository.class);
        zoneService = new ZoneService(zoneRepository);
    }

    @Test
    void testList() {
        when(zoneRepository.findAll()).thenReturn(Collections.emptyList());
        List<Zone> zones = zoneService.list();
        assertTrue(zones.isEmpty());
    }

    @Test
    void testReadZone_Existing() {
        Zone existingZone = new Zone();
        existingZone.setZoneID(1);
        when(zoneRepository.findById(1)).thenReturn(Optional.of(existingZone));

        Zone zone = zoneService.readZone(1);

        assertNotNull(zone);
        assertEquals(existingZone, zone);
    }

    @Test
    void testReadZone_NonExisting() {
        when(zoneRepository.findById(1)).thenReturn(Optional.empty());

        Zone zone = zoneService.readZone(1);

        assertNull(zone);
    }

    @Test
    void testCreateZone() {
        String description = "Urola Erdia";

        zoneService.createZone(description);

        verify(zoneRepository, times(1)).save(any(Zone.class));
    }

    @Test
    void testUpdateZone() {
        Integer zoneID = 1;
        String description = "Urola Garaia";
        Zone existingZone = new Zone();
        existingZone.setZoneID(zoneID);
        when(zoneRepository.findById(zoneID)).thenReturn(Optional.of(existingZone));

        zoneService.updateZone(zoneID, description);

        assertEquals(description, existingZone.getDescription());
        verify(zoneRepository, times(1)).save(existingZone);
    }

    @Test
    void testDeleteZone() {
        Integer zoneID = 1;
        Zone existingZone = new Zone();
        existingZone.setZoneID(zoneID);
        when(zoneRepository.findById(zoneID)).thenReturn(Optional.of(existingZone));

        zoneService.deleteZone(zoneID);

        verify(zoneRepository, times(1)).delete(existingZone);
    }
}

