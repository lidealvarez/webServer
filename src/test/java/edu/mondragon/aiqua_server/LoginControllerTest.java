package edu.mondragon.aiqua_server;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.mondragon.aiqua_server.controllers.LoginController;
import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.services.UserService;
import edu.mondragon.aiqua_server.services.UserTypeService;
import edu.mondragon.aiqua_server.services.ZoneService;

class LoginControllerTest {

    private UserService userService;
    private UserTypeService userTypeService;
    private ZoneService zoneService;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userTypeService = mock(UserTypeService.class);
        zoneService = mock(ZoneService.class);
        loginController = new LoginController(userService, userTypeService, zoneService);
    }

    @Test
    void testUserLogin_ValidCredentials() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);

        when(userService.readUser("nagore")).thenReturn(user);

        ResponseEntity<User> response = loginController.userLogin(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUserLogin_InvalidCredentials() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);

        when(userService.readUser("user")).thenReturn(user);

        user.setPassword("wrongPassword");

        ResponseEntity<User> response = loginController.userLogin(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUserLogin_UserNotFound() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "noexist", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType,
                zones);
        when(userService.readUser("noexist")).thenReturn(null);

        ResponseEntity<User> response = loginController.userLogin(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUserRegister_ValidUser() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = new User(1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com", userType, zones);

        when(userService.createUser(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(user);

        ResponseEntity<User> response = loginController.userRegister(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
}
