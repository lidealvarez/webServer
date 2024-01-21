package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.Data;
import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;

class DataTest {

    @Test
    void testDataIDGettersAndSetters() {
        Data data = new Data();
        data.setDataID(1);
        assertEquals((Integer)1, data.getDataID());
    }

    @Test
    void testTimestampGettersAndSetters() {
        Data data = new Data();
        Date timestamp = new Date(2024-01-21);
        data.setTimestamp(timestamp);
        assertEquals(timestamp, data.getTimestamp());
    }

    @Test
    void testFlowGettersAndSetters() {
        Data data = new Data();
        data.setFlow(42);
        assertEquals((Integer)42, data.getFlow());
    }


    @Test
    void testPressureGettersAndSetters() {
        Data data = new Data();
        data.setPressure(3.14);
        assertEquals(3.14, data.getPressure(), 0.01);
    }

    @Test
    void testReductorGettersAndSetters() {
        Data data = new Data();
        Reductor reductor = new Reductor(1, "Berazubi", 0.92, new Town(1, "Tolosa", new Zone(1, "Tolosaldea")));
        data.setReductor(reductor);
        assertEquals(reductor, data.getReductor());
    }

    @Test
    void testConstructor() {
        Reductor reductor = new Reductor(1, "Berazubi", 0.92, new Town(1, "Tolosa", new Zone(1, "Tolosaldea")));
        Date timestamp = new Date(2024-01-21);
        Data data = new Data(1, timestamp, 40, 3.4, reductor);
        assertEquals((Integer)1, data.getDataID());
        assertEquals(timestamp, data.getTimestamp());
        assertEquals((Integer)40, data.getFlow());
        assertEquals(3.4, data.getPressure(), 0.01);
        assertEquals(reductor, data.getReductor());
    }
}
