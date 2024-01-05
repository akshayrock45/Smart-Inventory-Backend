package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.UsersDTO;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.exceptions.IncorrectPasswordException;
import com.incedo.capstone.smartinventory.exceptions.UserCreationException;
import com.incedo.capstone.smartinventory.exceptions.UserNotFoundException;
import com.incedo.capstone.smartinventory.mapper.UsersMapper;
import com.incedo.capstone.smartinventory.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public String addUser(Users user) {
//        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        String encryptedPwd = bcrypt.encode(user.getPwd());
//        user.setPwd(encryptedPwd);

        Users existingUser = usersRepository.findByEmail(user.getEmail());



        if(existingUser != null && existingUser.getEmail().equals(user.getEmail()))
        {
            throw  new UserCreationException("User Already Exist! with the same Email ");
        }
        else {
            Users savedUser = usersRepository.save(user);
            if (savedUser != null) {
                return "User Created";
            }
            throw new UserCreationException("There is Some Problem Creating the User");
        }

//        boolean existingUser = usersRepository.existsById(user.getUsername());

//        if(existingUser)
//        {
//            throw  new UserCreationException("User Already Exist!");
//        }
//        else {
//            Users savedUser = usersRepository.save(user);
//            if (savedUser != null) {
//                return "User Created";
//            }
//            throw new UserCreationException("There is Some Problem Creating the User");
//        }

    }


    public UsersDTO updateUser(long userId, UsersDTO updatedUserDto) {
        // Find the existing user by username
        Optional<Users>  op = usersRepository.findById(userId);

        if (op.isPresent()) {
            // Update the user with the new values
            Users existingUser = op.get();
            existingUser.setUsername(updatedUserDto.getUsername());
            existingUser.setRole(updatedUserDto.getRole());
            existingUser.setMobileNumber(updatedUserDto.getMobileNumber());
            existingUser.setGender(updatedUserDto.getGender());
            existingUser.setEmail(updatedUserDto.getEmail());


            // Save the updated user
            Users updatedUser = usersRepository.save(existingUser);

            // Convert and return the updated user DTO
            return UsersMapper.convertToDto(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with username: " + userId);
        }
    }

    public List<UsersDTO> fetchUsers(){
        return usersRepository.findAll()
                .stream().
                map(UsersMapper::convertToDto).
                collect(Collectors.toList());
    }

//    public List<UsersDTO> fetchUserByName(String username) {
//        Users user = usersRepository.findByUsername(username);
//
//        try {
//            List<Users> users = usersRepository.findByUsernameList(username)
//                    .stream().
//                    map(UsersMapper::convertToDto).
//                    collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
////        if (user != null) {
////            return UsersMapper.convertToDto(user);
////        } else {
////            throw new UserNotFoundException("No Record found for the user: " + username);
////        }
//    }

    public List<UsersDTO> fetchUserByName(String username) {
        List<Users> users = usersRepository.findByUsernameContaining(username);

        if (users.isEmpty()) {
            throw new UserNotFoundException("No Records found for the username pattern: " + username);
        }

        return users.stream()
                .map(UsersMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public String deleteUser(String username) {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            usersRepository.delete(user);
            return "User Successfully Deleted";
        } else {
            throw new UserNotFoundException("No Record found for user: " + username);
        }
    }


    public UsersDTO authenticateUser(Users user) {
        // Find user by username
        Users existingUser = usersRepository.findByUsername(user.getUsername());

        if (existingUser != null) {

            if(existingUser.getPwd().matches(user.getPwd()))
            {
                return UsersMapper.convertToDto(existingUser);
            }
            else
            {
                throw new IncorrectPasswordException("Incorrect password for user: " + user.getUsername());
            }

        } else {
            // User does not exist, throw an exception
            throw new UserNotFoundException("User not found: " + user.getUsername());
        }
    }

    public UsersDTO fetchById(long userId) {
        Optional<Users> op = usersRepository.findById(userId);

        if(op.isPresent())
        {
            Users existingUser = op.get();

            return UsersMapper.convertToDto(existingUser);
        }
        throw new UserNotFoundException("No records found for: " + userId);
    }

//    public List<UsersDTO> fetchAllUsersByName(String Username)
//    {
//        return usersRepository.findAll()
//                .stream().
//                map(UsersMapper::convertToDto).
//                collect(Collectors.toList());
//
//    }
}
