package edu.mondragon.aiqua_server;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.UserRepository;
import edu.mondragon.aiqua_server.services.UserService;

class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        List<User> users = userService.list();
        assertTrue(users.isEmpty());
    }

    @Test
    void testReadUser_UsernameAndPassword() {
        when(userRepository.findByUsernameAndPassword("nagore", "nagore123")).thenReturn(Collections.emptyList());
        User user = userService.readUser("nagore", "nagore123");
        assertNull(user);
    }

    @Test
    void testReadUser_UsernameAndPassword_EmptyList() {
        when(userRepository.findByUsernameAndPassword("no", "password"))
                .thenReturn(Collections.emptyList());
        User user = userService.readUser("no", "password");
        assertNull(user);
    }

    @Test
    void testReadUserZones() {
        User user = new User();
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        user.setZones(zones);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        List<Zone> zoneListWithResult = userService.readUserZones(1);
        assertNotNull(zoneListWithResult);
        assertEquals(zones, zoneListWithResult);

        // List zones empty
        when(userRepository.findById(2)).thenReturn(Optional.of(new User()));

        List<Zone> emptyZoneList = userService.readUserZones(2);
        assertNull(emptyZoneList);
    }

    @Test
    void testReadUser_Username() {
        User existingUser = new User();
        when(userRepository.findByUsername("nagore")).thenReturn(Collections.singletonList(existingUser));
        User user = userService.readUser("nagore");

        assertNotNull(user);
        assertEquals(existingUser, user);

        // Empty user list
        when(userRepository.findByUsername("no")).thenReturn(Collections.emptyList());
        User nonExistingUser = userService.readUser("no");
        assertNull(nonExistingUser);
    }

    @Test
    void testReadUser_UserId() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        User user = userService.readUser(1);
        assertNull(user);
    }

    @Test
    void testCreateUser() {
        UserType userType = new UserType(1, "Administrator");
        List<Zone> zones = new ArrayList<>();
        zones.add(new Zone(1, "Urola Erdia"));
        zones.add(new Zone(2, "Urola Garaia"));
        User user = userService.createUser("nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com",
                userType, zones);

        assertNotNull(user);
        assertEquals("nagore", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("Nagore", user.getName());
        assertEquals("Elduaien", user.getSurname());
        assertEquals("67890123", user.getPhone());
        assertEquals("nagore@gmail.com", user.getEmail());
        assertEquals(userType, user.getUserType());
        assertEquals(zones, user.getZones());
    }

    @Test
    void testUpdateUser() {
        UserType userType = new UserType(1, "Administrator");

        User existingUser = new User();
        existingUser.setUserID(1);
        existingUser.setUsername("n");
        existingUser.setPassword("e");
        existingUser.setName("n");
        existingUser.setSurname("e");
        existingUser.setPhone("123456789");
        existingUser.setEmail("n.e@gmail.com");
        existingUser.setUserType(new UserType(1, "Manteinance"));

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        userService.updateUser((Integer) 1, "nagore", "123", "Nagore", "Elduaien", "67890123", "nagore@gmail.com",
                userType);

        assertEquals("nagore", existingUser.getUsername());
        assertEquals("123", existingUser.getPassword());
        assertEquals("Nagore", existingUser.getName());
        assertEquals("Elduaien", existingUser.getSurname());
        assertEquals("67890123", existingUser.getPhone());
        assertEquals("nagore@gmail.com", existingUser.getEmail());
        assertEquals(userType, existingUser.getUserType());
    }

    @Test
    void testDeleteUser() {
        User existingUser = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        userService.deleteUser(1);
        verify(userRepository, times(1)).delete(existingUser);
    }
}
