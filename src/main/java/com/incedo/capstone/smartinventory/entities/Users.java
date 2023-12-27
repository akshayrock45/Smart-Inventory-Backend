package com.incedo.capstone.smartinventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Id
    private String username;
    private String pwd;
    private String role;
    private long mobileNumber;
    private String gender;

}
