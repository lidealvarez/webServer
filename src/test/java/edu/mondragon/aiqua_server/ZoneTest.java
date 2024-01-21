package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;

class ZoneTest {

    @Test
    void testZoneIDGettersAndSetters() {
        Zone zone = new Zone();
        zone.setZoneID(1);
        assertEquals((Integer)1, zone.getZoneID());
    }

    @Test
    void testDescriptionGettersAndSetters() {
        Zone zone = new Zone();
        zone.setDescription("Urola Kosta");
        assertEquals("Urola Kosta", zone.getDescription());
    }

    @Test
    void testUsersGettersAndSetters() {
        Zone zone = new Zone();
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        List<User> users = new ArrayList<>();
        users.add(new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones));
        users.add(new User(2, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones));
        zone.setUsers(users);
        assertEquals(users, zone.getUsers());
    }

    @Test
    void testConstructor() {
        Zone zone = new Zone(1, "Administrator");
        assertEquals((Integer)1, zone.getZoneID());
        assertEquals("Administrator", zone.getDescription());
    }
}
