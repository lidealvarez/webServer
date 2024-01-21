package edu.mondragon.aiqua_server;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.repositories.UserTypeRepository;
import edu.mondragon.aiqua_server.services.UserTypeService;

class UserTypeServiceTest {

    @MockBean
    private UserTypeRepository userTypeRepository;

    private UserTypeService userTypeService;

    @BeforeEach
    void setUp() {
        userTypeRepository = mock(UserTypeRepository.class);
        userTypeService = new UserTypeService(userTypeRepository);
    }

    @Test
    void testList() {
        when(userTypeRepository.findAll()).thenReturn(Collections.emptyList());
        List<UserType> types = userTypeService.list();
        assertTrue(types.isEmpty());
    }

    @Test
    void testCreateType() {
        String name = "Administrator";

        userTypeService.createType(name);

        verify(userTypeRepository, times(1)).save(any(UserType.class));
    }

    @Test
    void testReadType_ExistingType() {
        UserType existingType = new UserType();
        existingType.setUserTypeID(1);
        when(userTypeRepository.findById(1)).thenReturn(Optional.of(existingType));

        UserType type = userTypeService.readType(1);

        assertNotNull(type);
        assertEquals(existingType, type);
    }

    @Test
    void testReadType_NonExistingType() {
        when(userTypeRepository.findById(1)).thenReturn(Optional.empty());

        UserType type = userTypeService.readType(1);

        assertNull(type);
    }

    @Test
    void testUpdateType() {
        Integer userTypeID = 1;
        String name = "Maintenance";
        UserType existingType = new UserType();
        existingType.setUserTypeID(userTypeID);
        when(userTypeRepository.findById(userTypeID)).thenReturn(Optional.of(existingType));

        userTypeService.updateType(userTypeID, name);

        assertEquals(name, existingType.getDescription());
        verify(userTypeRepository, times(1)).save(existingType);
    }

    @Test
    void testDeleteType() {
        Integer userTypeID = 1;
        UserType existingType = new UserType();
        existingType.setUserTypeID(userTypeID);
        when(userTypeRepository.findById(userTypeID)).thenReturn(Optional.of(existingType));

        userTypeService.deleteType(userTypeID);

        verify(userTypeRepository, times(1)).delete(existingType);
    }
}
