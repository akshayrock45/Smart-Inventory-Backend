package com.incedo.capstone.smartinventory.services;//package com.incedo.capstone.smartinventory.services;


import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.exceptions.IncorrectPasswordException;
import com.incedo.capstone.smartinventory.exceptions.UserCreationException;
import com.incedo.capstone.smartinventory.exceptions.UserNotFoundException;
import com.incedo.capstone.smartinventory.mapper.UsersMapper;
import com.incedo.capstone.smartinventory.repository.UsersRepository;
import com.incedo.capstone.smartinventory.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void addUser_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPwd("password");

        when(usersRepository.findByEmail("test@example.com")).thenReturn(null);
        when(usersRepository.save(user)).thenReturn(user);

        String result = usersService.addUser(user);

        assertEquals("User Created", result);
    }

    @Test
    void addUser_UserAlreadyExists() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPwd("password");

        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);

        assertThrows(UserCreationException.class, () -> usersService.addUser(user));
    }

    @Test
    void authenticateUser_Success() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPwd("password");

        Users existingUser = new Users();
        existingUser.setUsername("testuser");
        existingUser.setPwd(new BCryptPasswordEncoder().encode("password"));

        when(usersRepository.findByUsername("testuser")).thenReturn(existingUser);

        UsersDTO result = usersService.authenticateUser(user);

        assertNotNull(result);
        assertEquals(existingUser.getUsername(), result.getUsername());
    }

    @Test
    void authenticateUser_UserNotFound() {
        Users user = new Users();
        user.setUsername("nonexistinguser");
        user.setPwd("password");

        when(usersRepository.findByUsername("nonexistinguser")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> usersService.authenticateUser(user));
    }

    @Test
    void authenticateUser_IncorrectPassword() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPwd("wrongpassword");

        Users existingUser = new Users();
        existingUser.setUsername("testuser");
        existingUser.setPwd(new BCryptPasswordEncoder().encode("password"));

        when(usersRepository.findByUsername("testuser")).thenReturn(existingUser);

        assertThrows(IncorrectPasswordException.class, () -> usersService.authenticateUser(user));
    }

    // Add more test cases for other methods as needed

}
