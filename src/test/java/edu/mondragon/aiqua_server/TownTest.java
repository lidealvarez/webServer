package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;

class TownTest {

    @Test
    void testTownIDGettersAndSetters() {
        Town town = new Town();
        town.setTownID(1);
        assertEquals((Integer)1, town.getTownID());
    }

    @Test
    void testDescriptionGettersAndSetters() {
        Town town = new Town();
        town.setDescription("Azkoitia");
        assertEquals("Azkoitia", town.getDescription());
    }

    @Test
    void testZoneGettersAndSetters() {
        Zone zone = new Zone(1, "Urola Kosta");
        Town town = new Town();
        town.setZone(zone);
        assertEquals(zone, town.getZone());
    }

    @Test
    void testConstructor() {
        Zone zone = new Zone(1, "Urola Kosta");
        Town town = new Town(1, "Azkoitia", zone);
        assertEquals((Integer)1, town.getTownID());
        assertEquals("Azkoitia", town.getDescription());
        assertEquals(zone, town.getZone());
    }
}
