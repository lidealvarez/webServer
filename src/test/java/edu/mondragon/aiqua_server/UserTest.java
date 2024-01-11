package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.User;

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
}
