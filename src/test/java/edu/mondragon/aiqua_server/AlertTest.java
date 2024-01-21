package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.Alert;
import edu.mondragon.aiqua_server.models.Data;
import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;

class AlertTest {

    @Test
    void testAlertIDGettersAndSetters() {
        Alert alert = new Alert();
        alert.setAlertID(1);
        assertEquals((Integer)1, alert.getAlertID());
    }

    @Test
    void testAlertMessageGettersAndSetters() {
        Alert alert = new Alert();
        alert.setAlertMessage("Anomaly detected!");
        assertEquals("Anomaly detected!", alert.getAlertMessage());
    }

    @Test
    void testSeverityGettersAndSetters() {
        Alert alert = new Alert();
        alert.setSeverity("Mild anomaly");
        assertEquals("Mild anomaly", alert.getSeverity());
    }

    @Test
    void testStatusGettersAndSetters() {
        Alert alert = new Alert();
        alert.setStatus(true);
        assertEquals(true, alert.isStatus());
    }

    @Test
    void testUserGettersAndSetters() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);
        Alert alert = new Alert();
        alert.setUser(user);
        assertEquals(user, alert.getUser());
    }

    @Test
    void testDataGettersAndSetters() {
        Reductor reductor = new Reductor(1, "Berazubi", 0.92, new Town(1, "Tolosa", new Zone(1, "Tolosaldea")));
        Date timestamp = new Date(2024-01-21);
        Data data = new Data(1, timestamp, 40, 3.4, reductor);
        Alert alert = new Alert();
        alert.setData(data);
        assertEquals(data, alert.getData());
    }

    @Test
    void testConstructor() {
        Reductor reductor = new Reductor(1, "Berazubi", 0.92, new Town(1, "Tolosa", new Zone(1, "Tolosaldea")));
        Date timestamp = new Date(2024-01-21);
        Data data = new Data(1, timestamp, 40, 3.4, reductor);

        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);

        Alert alert = new Alert(1, "Anomaly detected!", "Mild anomaly", true, user, data);

        assertEquals((Integer)1, alert.getAlertID());
        assertEquals("Anomaly detected!", alert.getAlertMessage());
        assertEquals("Mild anomaly", alert.getSeverity());
        assertEquals(true, alert.isStatus());
        assertEquals(user, alert.getUser());
        assertEquals(data, alert.getData());
    }
}
