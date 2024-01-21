package edu.mondragon.aiqua_server;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.mondragon.aiqua_server.controllers.UserController;
import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.services.ReductorService;
import edu.mondragon.aiqua_server.services.TownService;
import edu.mondragon.aiqua_server.services.UserService;
import edu.mondragon.aiqua_server.services.ZoneService;

class UserControllerTest {

    private ZoneService zoneService;
    private TownService townService;
    private UserService userService;
    private ReductorService reductorService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        zoneService = mock(ZoneService.class);
        townService = mock(TownService.class);
        userService = mock(UserService.class);
        reductorService = mock(ReductorService.class);
        userController = new UserController(zoneService, townService, userService, reductorService);
    }

    @Test
    void testGetZones_UserWithZones() {
        int userID = 1;
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone());
        when(userService.readUserZones(userID)).thenReturn(zones);

        ResponseEntity<List<Zone>> response = userController.getZones(userID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zones, response.getBody());
    }

    @Test
    void testGetZones_UserWithoutZones() {
        int userID = 2;
        when(userService.readUserZones(userID)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Zone>> response = userController.getZones(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetTownsByZone_WithTowns() {
        int zoneID = 1;
        List<Town> towns = new ArrayList<>();
        towns.add(new Town());
        when(townService.readTownsByZone(zoneID)).thenReturn(towns);

        ResponseEntity<List<Town>> response = userController.getTownsByZone(zoneID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(towns, response.getBody());
    }

    @Test
    void testGetTownsByZone_WithoutTowns() {
        int zoneID = 2;
        when(townService.readTownsByZone(zoneID)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Town>> response = userController.getTownsByZone(zoneID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetReductorsByTown_WithReductors() {
        String townDescription = "Azkoitia";
        Town town = new Town();
        List<Reductor> reductors = new ArrayList<>();
        reductors.add(new Reductor());
        when(townService.readTownByDescription(townDescription)).thenReturn(town);
        when(reductorService.readReductorsByTown(town)).thenReturn(reductors);

        ResponseEntity<List<Reductor>> response = userController.getReductorsByTown(townDescription);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reductors, response.getBody());
    }

    @Test
    void testGetReductorsByTown_WithoutReductors() {
        String townDescription = "Azkoitia";
        Town town = new Town();
        when(townService.readTownByDescription(townDescription)).thenReturn(town);
        when(reductorService.readReductorsByTown(town)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Reductor>> response = userController.getReductorsByTown(townDescription);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
