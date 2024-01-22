package com.incedo.capstone.smartinventory.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incedo.capstone.smartinventory.controllers.GodownsController;
import com.incedo.capstone.smartinventory.dto.GodownsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.exceptions.GodownNotFoundException;
import com.incedo.capstone.smartinventory.services.GodownsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GodownsControllerTest {

    @Mock
    private GodownsService godownsService;

    @InjectMocks
    private GodownsController godownsController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void addGodown_validGodown_shouldReturnCreatedStatus() {

        Godowns godown = new Godowns();
        when(godownsService.addGodown(any())).thenReturn("Godown added successfully");

        ResponseEntity<String> responseEntity = godownsController.addGodown(godown);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Godown added successfully", responseEntity.getBody());
    }

    @Test
    void updateGodown_validGodown_shouldReturnOkStatus() {

        long godownId = 1L;
        GodownsDTO godownsDTO = new GodownsDTO();
        when(godownsService.updateGodown(godownId, godownsDTO)).thenReturn(godownsDTO);

        ResponseEntity<Object> responseEntity = godownsController.updateGodown(godownId, godownsDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(godownsDTO, responseEntity.getBody());
    }

    @Test
    void updateGodown_godownNotFound_shouldReturnNotFoundStatus() {

        long godownId = 1L;
        GodownsDTO godownsDTO = new GodownsDTO();
        when(godownsService.updateGodown(godownId, godownsDTO)).thenThrow(GodownNotFoundException.class);


        ResponseEntity<Object> responseEntity = godownsController.updateGodown(godownId, godownsDTO);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateGodown_exceptionThrown_shouldReturnInternalServerErrorStatus() {

        long godownId = 1L;
        GodownsDTO godownsDTO = new GodownsDTO();

        when(godownsService.updateGodown(godownId, godownsDTO)).thenThrow(GodownNotFoundException.class);

        ResponseEntity<Object> responseEntity = godownsController.updateGodown(godownId, godownsDTO);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void changeStatus_validGodown_shouldReturnOkStatus() {

        long godownId = 1L;
        when(godownsService.changeStatus(godownId)).thenReturn("Godown status changed successfully");

        ResponseEntity<String> responseEntity = godownsController.changeStatus(godownId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Godown status changed successfully", responseEntity.getBody());
    }

    @Test
    void changeStatus_godownNotFound_shouldReturnNotFoundStatus() {

        long godownId = 1L;
        when(godownsService.changeStatus(godownId)).thenThrow(GodownNotFoundException.class);

        ResponseEntity<String> responseEntity = godownsController.changeStatus(godownId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void fetchGodowns_shouldReturnListOfGodownsDTO() {

        List<GodownsDTO> godownsDTOList = new ArrayList<>();
        when(godownsService.fetchGodowns()).thenReturn(godownsDTOList);

        List<GodownsDTO> result = godownsController.fetchGodowns();

        assertNotNull(result);
        assertEquals(godownsDTOList, result);
    }

    @Test
    void getByLocation_validLocation_shouldReturnListOfGodownsDTO() {

        String location = "TestLocation";
        List<GodownsDTO> godownsDTOList = new ArrayList<>();
        when(godownsService.fetchByLocation(location)).thenReturn(godownsDTOList);

        ResponseEntity<Object> responseEntity = godownsController.getByLocation(location);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(godownsDTOList, responseEntity.getBody());
    }

    @Test
    void getByLocation_locationNotFound_shouldReturnNotFoundStatus() {

        String location = "NonExistentLocation";
        when(godownsService.fetchByLocation(location)).thenThrow(GodownNotFoundException.class);

        ResponseEntity<Object> responseEntity = godownsController.getByLocation(location);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getById_validGodownId_shouldReturnGodownsDTO() {

        long godownId = 1L;
        GodownsDTO godownsDTO = new GodownsDTO();
        when(godownsService.fetchById(godownId)).thenReturn(godownsDTO);

        ResponseEntity<Object> responseEntity = godownsController.getById(godownId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(godownsDTO, responseEntity.getBody());
    }

    @Test
    void getById_godownNotFound_shouldReturnNotFoundStatus() {

        long godownId = 1L;
        when(godownsService.fetchById(godownId)).thenThrow(GodownNotFoundException.class);

        ResponseEntity<Object> responseEntity = godownsController.getById(godownId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteById_validGodownId_shouldReturnOkStatus() {

        long godownId = 1L;
        when(godownsService.deleteById(godownId)).thenReturn("Godown deleted successfully");


        ResponseEntity<String> responseEntity = godownsController.deleteById(godownId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Godown deleted successfully", responseEntity.getBody());
    }

    @Test
    void deleteById_godownNotFound_shouldReturnNotFoundStatus() {

        long godownId = 1L;
        when(godownsService.deleteById(godownId)).thenThrow(GodownNotFoundException.class);


        ResponseEntity<String> responseEntity = godownsController.deleteById(godownId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void fetchAllGodowns_shouldReturnListOfGodowns() {

        List<Godowns> godownsList = new ArrayList<>();
        when(godownsService.fetchGodowns2()).thenReturn(godownsList);

        List<Godowns> result = godownsController.fetchAllGodowns();

        assertNotNull(result);
        assertEquals(godownsList, result);
    }
}
