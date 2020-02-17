package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.junit.jupiter.api.Test;

import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationUserTest {


    @Test
    void getUserName() {
        ApplicationUser testUser = new ApplicationUser();
        testUser.getId();
        System.out.println("testUser = " + testUser.getId());
        assertTrue(testUser.getId() == 0);
    }

    @Test
    void getFirstName() {
        ApplicationUser testUser = new ApplicationUser();
        testUser.setFirstName("testFirst");
        assertTrue(testUser.getFirstName() == "testFirst");
    }

    @Test
    void getLastName() {

        ApplicationUser testUser = new ApplicationUser();
        testUser.setLastName("testLast");
        assertTrue(testUser.getLastName() == "testLast");
    }

    @Test
    void getBoards() {
        ApplicationUser testUser = new ApplicationUser();
        assertNull(testUser.getBoards());
    }
    
    @Test
    void getPassword() {
        ApplicationUser testUser = new ApplicationUser();
        assertNull(testUser.getPassword());
    }

    @Test
    void getUsername() {
        ApplicationUser testUser = new ApplicationUser();
        assertNull(testUser.getUserName());
    }
}