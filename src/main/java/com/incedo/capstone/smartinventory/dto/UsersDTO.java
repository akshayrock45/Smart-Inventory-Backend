package com.incedo.capstone.smartinventory.dto;

import lombok.Data;

public class UsersDTO {

//    private Long userId;
    private String username;
    private String role;
    private long mobileNumber;
    private String gender;

    @Override
    public String toString() {
        return "UsersDTO{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", gender='" + gender + '\'' +
                '}';
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
