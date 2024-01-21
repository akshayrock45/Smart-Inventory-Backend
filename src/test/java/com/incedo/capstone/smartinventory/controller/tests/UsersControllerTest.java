package com.incedo.capstone.smartinventory.controller.tests;

import com.incedo.capstone.smartinventory.controllers.UsersController;
import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.services.UsersService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeAll
    public static void TestStartMessage() {
        System.out.println("Test Starting at : " + new Date());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddUser() {
        Users user = new Users();
        when(usersService.addUser(user)).thenReturn("User added successfully");

        ResponseEntity<String> responseEntity = usersController.addUser(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User added successfully", responseEntity.getBody());
        verify(usersService, times(1)).addUser(user);
    }

    @Test
    public void testUpdateUserById() {
        long userId = 1L;
        UsersDTO updatedUserDto = new UsersDTO(); // create a sample updated user DTO

        when(usersService.updateUser(userId, updatedUserDto)).thenReturn(updatedUserDto);

        ResponseEntity<Object> responseEntity = usersController.updateUserById(userId, updatedUserDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUserDto, responseEntity.getBody());
        verify(usersService, times(1)).updateUser(userId, updatedUserDto);
    }

    // Add similar tests for other controller methods...

    @Test
    public void testAuthenticateUser() {
        Users user = new Users(); // create a sample user

        when(usersService.authenticateUser(user)).thenReturn(new UsersDTO());

        ResponseEntity<Object> responseEntity = usersController.authenticateUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(UsersDTO.class, responseEntity.getBody().getClass());
        verify(usersService, times(1)).authenticateUser(user);
    }

    // Add more tests as needed...
}
