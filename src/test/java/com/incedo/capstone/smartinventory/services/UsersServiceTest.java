package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.exceptions.IncorrectPasswordException;
import com.incedo.capstone.smartinventory.exceptions.UserCreationException;

import com.incedo.capstone.smartinventory.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser_Success() {
        Users newUser = new Users(); // create a sample user
        newUser.setEmail("test@example.com");

        when(usersRepository.findByEmail("test@example.com")).thenReturn(null);
        when(usersRepository.save(newUser)).thenReturn(newUser);

        String result = usersService.addUser(newUser);

        assertEquals("User Created", result);
        verify(usersRepository, times(1)).findByEmail("test@example.com");
        verify(usersRepository, times(1)).save(newUser);
    }

    @Test
    public void testAddUser_UserAlreadyExists() {
        Users existingUser = new Users(); // create a sample existing user
        existingUser.setEmail("test@example.com");

        when(usersRepository.findByEmail("test@example.com")).thenReturn(existingUser);

        assertThrows(UserCreationException.class, () -> usersService.addUser(existingUser));
        verify(usersRepository, times(1)).findByEmail("test@example.com");
        verify(usersRepository, never()).save(existingUser);
    }


    @Test
    public void testAuthenticateUser_Success() {
        Users existingUser = new Users(); // create a sample existing user
        existingUser.setUsername("testuser");
        existingUser.setPwd("password123");

        Users inputUser = new Users();
        inputUser.setUsername("testuser");
        inputUser.setPwd("password123");

        when(usersRepository.findByUsername("testuser")).thenReturn(existingUser);

        UsersDTO result = usersService.authenticateUser(inputUser);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(usersRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testAuthenticateUser_IncorrectPassword() {
        Users existingUser = new Users(); // create a sample existing user
        existingUser.setUsername("testuser");
        existingUser.setPwd("password123");

        Users inputUser = new Users();
        inputUser.setUsername("testuser");
        inputUser.setPwd("wrongpassword");

        when(usersRepository.findByUsername("testuser")).thenReturn(existingUser);

        assertThrows(IncorrectPasswordException.class, () -> usersService.authenticateUser(inputUser));
        verify(usersRepository, times(1)).findByUsername("testuser");

    }


}