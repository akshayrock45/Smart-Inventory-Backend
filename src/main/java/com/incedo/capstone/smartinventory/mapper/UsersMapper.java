package com.incedo.capstone.smartinventory.mapper;

import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import org.modelmapper.ModelMapper;

public class UsersMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static UsersDTO toDto(Users userEntity) {
        return modelMapper.map(userEntity, UsersDTO.class);
    }

    public static Users toEntity(UsersDTO userDTO) {
        return modelMapper.map(userDTO, Users.class);
    }
}
