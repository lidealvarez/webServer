package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;

class UserTest {

    @Test
    void testUserIDGettersAndSetters() {
        User user = new User();
        user.setUserID(1);
        assertEquals((Integer)1, user.getUserID());
    }

    @Test
    void testUsernameGettersAndSetters() {
        User user = new User();
        user.setUsername("nagore");
        assertEquals("nagore", user.getUsername());
    }

    @Test
    void testPasswordGettersAndSetters() {
        User user = new User();
        user.setPassword("123");
        assertEquals("123", user.getPassword());
    }

    @Test
    void testNameGettersAndSetters() {
        User user = new User();
        user.setName("Nagore");
        assertEquals("Nagore", user.getName());
    }

    @Test
    void testSurnameGettersAndSetters() {
        User user = new User();
        user.setSurname("Elduaien");
        assertEquals("Elduaien", user.getSurname());
    }

    @Test
    void testPhoneGettersAndSetters() {
        User user = new User();
        user.setPhone("123456");
        assertEquals("123456", user.getPhone());
    }

    @Test
    void testEmailGettersAndSetters() {
        User user = new User();
        user.setEmail("nagore@gmail.com");
        assertEquals("nagore@gmail.com", user.getEmail());
    }

    @Test
    void testUserTypeGettersAndSetters() {
        User user = new User();
        UserType userType = new UserType(1, "Administrator");
        user.setUserType(userType);
        assertEquals(userType, user.getUserType());
    }

    @Test
    void testZonesGettersAndSetters() {
        User user = new User();
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        user.setZones(zones);
        assertEquals(zones, user.getZones());
    }

    @Test
    void testConstructor() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);
        assertEquals((Integer)1, user.getUserID());
        assertEquals("nagore", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("Nagore", user.getName());
        assertEquals("Elduaien", user.getSurname());
        assertEquals("67890123", user.getPhone());
        assertEquals("nagore@gmail.com", user.getEmail());
        assertEquals(userType, user.getUserType());
        assertEquals(zones, user.getZones());
    }
}
