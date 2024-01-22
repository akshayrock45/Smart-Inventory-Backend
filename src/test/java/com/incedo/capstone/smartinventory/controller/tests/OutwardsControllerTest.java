package com.incedo.capstone.smartinventory.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incedo.capstone.smartinventory.controllers.OutwardsController;
import com.incedo.capstone.smartinventory.dto.OutwardsDTO;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.exceptions.OutwardsNotFoundException;
import com.incedo.capstone.smartinventory.services.OutwardsService;
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

public class OutwardsControllerTest {

    @Mock
    private OutwardsService outwardsService;

    @InjectMocks
    private OutwardsController outwardsController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void addOutwards_validOutwards_shouldReturnCreatedStatus() {

        Outwards outwards = new Outwards();
        when(outwardsService.addOutwards(any())).thenReturn("Outwards added successfully");

        ResponseEntity<String> responseEntity = outwardsController.addOutwards(outwards);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Outwards added successfully", responseEntity.getBody());
    }

    @Test
    void updateById_validOutwards_shouldReturnOkStatus() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDTO = new OutwardsDTO();
        when(outwardsService.updateById(outwardsId, outwardsDTO)).thenReturn(outwardsDTO);

        ResponseEntity<Object> responseEntity = outwardsController.UpdateById(outwardsId, outwardsDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(outwardsDTO, responseEntity.getBody());
    }

    @Test
    void updateById_outwardsNotFound_shouldReturnBadRequestStatus() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDTO = new OutwardsDTO();
        when(outwardsService.updateById(outwardsId, outwardsDTO)).thenThrow(OutwardsNotFoundException.class);

        ResponseEntity<Object> responseEntity = outwardsController.UpdateById(outwardsId, outwardsDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void updateById_exceptionThrown_shouldReturnInternalServerErrorStatus() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDTO = new OutwardsDTO();

        when(outwardsService.updateById(outwardsId, outwardsDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> responseEntity = outwardsController.UpdateById(outwardsId, outwardsDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void fetchOutwards_shouldReturnListOfOutwardsDTO() {

        List<OutwardsDTO> outwardsDTOList = new ArrayList<>();
        when(outwardsService.fetchOutwards()).thenReturn(outwardsDTOList);

        List<OutwardsDTO> result = outwardsController.fetchOutwards();

        assertNotNull(result);
        assertEquals(outwardsDTOList, result);
    }

    @Test
    void getOutwardsById_validOutwardsId_shouldReturnOutwardsDTO() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDTO = new OutwardsDTO();
        when(outwardsService.fetchById(outwardsId)).thenReturn(outwardsDTO);

        ResponseEntity<Object> responseEntity = outwardsController.getOutwardsById(outwardsId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(outwardsDTO, responseEntity.getBody());
    }

    @Test
    void getOutwardsById_outwardsNotFound_shouldReturnNotFoundStatus() {

        long outwardsId = 1L;
        when(outwardsService.fetchById(outwardsId)).thenThrow(OutwardsNotFoundException.class);

        ResponseEntity<Object> responseEntity = outwardsController.getOutwardsById(outwardsId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteById_validOutwardsId_shouldReturnOkStatus() {

        long outwardsId = 1L;
        when(outwardsService.deleteById(outwardsId)).thenReturn("Outwards deleted successfully");

        ResponseEntity<String> responseEntity = outwardsController.deleteById(outwardsId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Outwards deleted successfully", responseEntity.getBody());
    }

    @Test
    void deleteById_outwardsNotFound_shouldReturnNotFoundStatus() {

        long outwardsId = 1L;
        when(outwardsService.deleteById(outwardsId)).thenThrow(OutwardsNotFoundException.class);

        ResponseEntity<String> responseEntity = outwardsController.deleteById(outwardsId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
