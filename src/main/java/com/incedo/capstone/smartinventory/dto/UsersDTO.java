package com.incedo.capstone.smartinventory.dto;

import lombok.Data;

@Data
public class UsersDTO {

    private Long userId;
    private String username;
    private String role;
    private long mobileNumber;
    private String gender;
}
