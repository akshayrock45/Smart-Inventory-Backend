package com.incedo.capstone.smartinventory.controllers;

import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.exceptions.IncorrectPasswordException;
import com.incedo.capstone.smartinventory.exceptions.UserNotFoundException;
import com.incedo.capstone.smartinventory.mapper.UsersMapper;
import com.incedo.capstone.smartinventory.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping("/addUsers")
    @Operation(summary = "Add Customer Here")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        String message = usersService.addUser(user);
//        if(message.equals("User Created")) {
//            ResponseEntity<String> re = new ResponseEntity<>(message, HttpStatus.CREATED);
//            return re;
//        } else if (message.equals("User Already Exist!")) {
//
//            ResponseEntity<String> re = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        }
//        message = "Something Went Wrong";
//        ResponseEntity<String> re = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        return re;

        ResponseEntity<String> re = new ResponseEntity<>(message, HttpStatus.CREATED);
            return re;
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update Customer Here")
    public ResponseEntity<Object> updateUserByUsername(
            @PathVariable("username") String username,
            @RequestBody UsersDTO updatedUserDto) {

        try {
            UsersDTO updatedUser = usersService.updateUser(username, updatedUserDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Operation(summary = "Fetch All Customers")
    public List<UsersDTO> fetchUsers()
    {
        return usersService.fetchUsers();
    }


    @GetMapping("/{username}")
    @Operation(summary = "Fetch Customer By Name")
    public ResponseEntity<Object> getUserById(@PathVariable("username") String username) {
        try {
            UsersDTO userDto = usersService.fetchUserByName(username);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete Customer")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username) {
        try {
            String result = usersService.deleteUser(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticateUsers")
    @Operation(summary = "Authenticate Customer")
    public ResponseEntity<Object> authenticateUser(@RequestBody Users user) {
        try {
            UsersDTO authenticatedUser = usersService.authenticateUser(user);
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        } catch (UserNotFoundException | IncorrectPasswordException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while authenticating the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
