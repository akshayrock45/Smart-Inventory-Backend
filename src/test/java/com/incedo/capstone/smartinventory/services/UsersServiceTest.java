package com.incedo.capstone.smartinventory.services;//package com.incedo.capstone.smartinventory.services;

import java.util.Arrays;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

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
    public void testAddUser_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setMobileNumber(1234567890L);
        user.setPwd("password");

        Mockito.when(usersRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList());
        Mockito.when(usersRepository.findByMobileNumber(user.getMobileNumber())).thenReturn(Arrays.asList());
        Mockito.when(usersRepository.save(any(Users.class))).thenReturn(user);

        String result = usersService.addUser(user);

        assertEquals("User Created", result);
    }

    @Test
    public void testAddUser_UserExistsWithEmail() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setMobileNumber(1234567890L);
        user.setPwd("password");

        Mockito.when(usersRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList(user));

        UserCreationException exception = assertThrows(UserCreationException.class,
                () -> usersService.addUser(user));

        assertEquals("User Already Exists with the same Email", exception.getMessage());
    }

    @Test
    public void testAddUser_UserExistsWithMobileNumber() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setMobileNumber(1234567890L);
        user.setPwd("password");

        Mockito.when(usersRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList());
        Mockito.when(usersRepository.findByMobileNumber(user.getMobileNumber())).thenReturn(Arrays.asList(user));

        UserCreationException exception = assertThrows(UserCreationException.class,
                () -> usersService.addUser(user));

        assertEquals("User Already Exists with the same Mobile Number", exception.getMessage());
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
    @Test
    void resetPassword_Success() {
        String username = "testuser";
        String newPassword = "newPassword123";

        Users existingUser = new Users();
        existingUser.setUsername(username);
        existingUser.setPwd(new BCryptPasswordEncoder().encode("oldPassword"));

        when(usersRepository.findByUsername(username)).thenReturn(existingUser);
        when(usersRepository.save(existingUser)).thenReturn(existingUser);

        String result = usersService.resetPassword(username, newPassword);

        assertEquals("Password reset successful", result);
        assertTrue(new BCryptPasswordEncoder().matches(newPassword, existingUser.getPwd()));
        verify(usersRepository, times(1)).findByUsername(username);
        verify(usersRepository, times(1)).save(existingUser);
    }

    @Test
    void resetPassword_UserNotFound() {
        String username = "nonexistinguser";
        String newPassword = "newPassword123";

        when(usersRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> usersService.resetPassword(username, newPassword));
        verify(usersRepository, times(1)).findByUsername(username);
        verify(usersRepository, never()).save(any());
    }


}
