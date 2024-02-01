package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.GodownsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Users;
import com.incedo.capstone.smartinventory.exceptions.GodownCreationException;
import com.incedo.capstone.smartinventory.exceptions.GodownNotFoundException;
import com.incedo.capstone.smartinventory.mapper.GodownsMapper;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import com.incedo.capstone.smartinventory.repository.UsersRepository;
import com.incedo.capstone.smartinventory.services.GodownsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
@ExtendWith(MockitoExtension.class)
public class GodownsServiceTest {

    @Mock
    private GodownsRepository godownsRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private GodownsService godownsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddGodown_Successful() {
        Godowns godown = new Godowns();
        godown.setLocation("Test Location");
        godown.setCapacityInQuintals(100.0);
        godown.setStatus(true);

        Users user = new Users();
        user.setUserId(1L);
        godown.setUsers(user);

        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(new Users()));
        when(godownsRepository.findByLocation(anyString())).thenReturn(null);
        when(godownsRepository.save(any(Godowns.class))).thenReturn(godown);

        String result = godownsService.addGodown(godown);

        assertEquals("Godown Added", result);

        verify(godownsRepository).save(any(Godowns.class));
    }

    @Test
    void testAddGodown_UserNotFound_ExceptionThrown() {
        Godowns godown = new Godowns();
        Users user = new Users();
        user.setUserId(1L);
        godown.setUsers(user);

        when(usersRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GodownCreationException.class, () -> godownsService.addGodown(godown));

        verify(godownsRepository, never()).save(any(Godowns.class));
    }


    @Test
    void testAddGodown_UserAlreadyAssociated_ExceptionThrown() {
        Godowns godown = new Godowns();
        Users existingUser = new Users();
        existingUser.setUserId(1L);
        godown.setUsers(existingUser);

        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));
        when(godownsRepository.findByUsersUserId(anyLong())).thenReturn(new Godowns());

        assertThrows(GodownCreationException.class, () -> godownsService.addGodown(godown));

        verify(godownsRepository, never()).save(any(Godowns.class));
    }

    @Test
    void testAddGodown_LocationAlreadyExists_ExceptionThrown() {
        Godowns godown = new Godowns();
        godown.setLocation("Test Location");
        godown.setUsers(new Users());

        lenient().when(usersRepository.findById(anyLong())).thenReturn(Optional.of(new Users()));
        lenient().when(godownsRepository.findByUsersUserId(anyLong())).thenReturn(null);
        lenient().when(godownsRepository.findByLocation(anyString())).thenReturn(new Godowns());

        assertThrows(GodownCreationException.class, () -> godownsService.addGodown(godown));

        verify(godownsRepository, never()).save(any(Godowns.class));
    }
// ...

    @Test
    void testUpdateGodown_Successful() {
        GodownsDTO godownsDTO = new GodownsDTO();
        godownsDTO.setLocation("Updated Location");
        godownsDTO.setCapacityInQuintals(150.0);
        godownsDTO.setStatus(true);

        Godowns existingGodown = new Godowns();
        existingGodown.setUsers(new Users());

        when(godownsRepository.findById(anyLong())).thenReturn(Optional.of(existingGodown));
        when(godownsRepository.save(any(Godowns.class))).thenReturn(existingGodown);

        GodownsDTO updatedGodown = godownsService.updateGodown(1L, godownsDTO);

        assertNotNull(updatedGodown);

        assertThat(updatedGodown, samePropertyValuesAs(GodownsMapper.convertToDto(existingGodown)));

        verify(godownsRepository).findById(anyLong());
        verify(godownsRepository).save(any(Godowns.class));
    }




    @Test
    void testUpdateGodown_GodownNotFoundException() {
        when(godownsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GodownNotFoundException.class, () -> godownsService.updateGodown(1L, new GodownsDTO()));

        verify(godownsRepository).findById(anyLong());

        verify(godownsRepository, never()).save(any(Godowns.class));
    }

    @Test
    void testFetchGodowns_Successful() {
        when(godownsRepository.findAll()).thenReturn(Collections.singletonList(new Godowns()));

        List<GodownsDTO> godownsDTOList = godownsService.fetchGodowns();

        assertNotNull(godownsDTOList);
        assertFalse(godownsDTOList.isEmpty());

        verify(godownsRepository).findAll();
    }

    @Test
    void testFetchByLocation_Successful() {
        String location = "Test Location";
        when(godownsRepository.findBylocationContaining(location)).thenReturn(Collections.singletonList(new Godowns()));

        List<GodownsDTO> godownsDTOList = godownsService.fetchByLocation(location);

        assertNotNull(godownsDTOList);
        assertFalse(godownsDTOList.isEmpty());

        verify(godownsRepository).findBylocationContaining(location);
    }

    @Test
    void testFetchByLocation_NoRecordsFound_ExceptionThrown() {
        String location = "Nonexistent Location";
        when(godownsRepository.findBylocationContaining(location)).thenReturn(Collections.emptyList());

        assertThrows(GodownNotFoundException.class, () -> godownsService.fetchByLocation(location));

        verify(godownsRepository).findBylocationContaining(location);
    }

    @Test
    void testFetchById_Successful() {
        Godowns existingGodown = new Godowns();
        when(godownsRepository.findById(anyLong())).thenReturn(Optional.of(existingGodown));

        GodownsDTO godownsDTO = godownsService.fetchById(1L);

        assertNotNull(godownsDTO);

        verify(godownsRepository).findById(anyLong());
    }

    @Test
    void testFetchById_GodownNotFoundException() {
        when(godownsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GodownNotFoundException.class, () -> godownsService.fetchById(1L));

        verify(godownsRepository).findById(anyLong());
    }
}

