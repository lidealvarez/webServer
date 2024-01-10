package edu.mondragon.aiqua_server;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.mondragon.aiqua_server.models.UserType;

class UserTypeTest {

    @Test
    void testUserTypeIDGettersAndSetters() {
        UserType userType = new UserType();
        userType.setUserTypeID(1);
        assertEquals((Integer)1, userType.getUserTypeID());
    }

    @Test
    void testDescriptionGettersAndSetters() {
        UserType userType = new UserType();
        userType.setDescription("Administrator");
        assertEquals("Administrator", userType.getDescription());
    }

    @Test
    void testConstructor() {
        UserType userType = new UserType(1, "Administrator");
        assertEquals((Integer)1, userType.getUserTypeID());
        assertEquals("Administrator", userType.getDescription());
    }
}
