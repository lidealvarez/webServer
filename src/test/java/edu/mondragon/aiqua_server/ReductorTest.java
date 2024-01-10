package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;

class ReductorTest {

    @Test
    void testReductorIDGettersAndSetters() {
        Reductor reductor = new Reductor();
        reductor.setReductorID(1);
        assertEquals((Integer)1, reductor.getReductorID());
    }

    @Test
    void testNameGettersAndSetters() {
        Reductor reductor = new Reductor();
        reductor.setName("Industrialdea");
        assertEquals("Industrialdea", reductor.getName());
    }

    @Test
    void testSensibilityGettersAndSetters() {
        Reductor reductor = new Reductor();
        reductor.setSensibility(2.4);
        assertEquals(2.4, reductor.getSensibility(), 0.01);
    }

    @Test
    void testConstructor() {
        Town town = new Town(1, "Azkoitia", new Zone(1, "Urola Erdia"));
        Reductor reductor = new Reductor(1, "Industrialdea", 2.4, town);
        assertEquals((Integer)1, reductor.getReductorID());
        assertEquals("Industrialdea", reductor.getName());
        assertEquals(2.4, reductor.getSensibility(), 0.01);
        assertEquals((Integer)1, reductor.getTown().getTownID());
        assertEquals("Azkoitia", reductor.getTown().getDescription());
        assertEquals((Integer)1, reductor.getTown().getZone().getZoneID());
        assertEquals("Urola Erdia", reductor.getTown().getZone().getDescription());
    }
}
