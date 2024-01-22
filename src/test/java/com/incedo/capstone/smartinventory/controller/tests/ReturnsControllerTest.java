package com.incedo.capstone.smartinventory.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incedo.capstone.smartinventory.controllers.ReturnsController;
import com.incedo.capstone.smartinventory.dto.ReturnsDTO;
import com.incedo.capstone.smartinventory.entities.Returns;
import com.incedo.capstone.smartinventory.exceptions.ReturnsNotFoundException;
import com.incedo.capstone.smartinventory.services.ReturnsService;
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

public class ReturnsControllerTest {

    @Mock
    private ReturnsService returnsService;

    @InjectMocks
    private ReturnsController returnsController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void fetchReturns_shouldReturnListOfReturnsDTO() {

        List<ReturnsDTO> returnsDTOList = new ArrayList<>();
        when(returnsService.fetchReturns()).thenReturn(returnsDTOList);

        List<ReturnsDTO> result = returnsController.fetchReturns();

        assertNotNull(result);
        assertEquals(returnsDTOList, result);
    }

    @Test
    void fetchReturns2_shouldReturnListOfReturns() {

        List<Returns> returnsList = new ArrayList<>();
        when(returnsService.fetchReturns2()).thenReturn(returnsList);

        List<Returns> result = returnsController.fetchReturns2();

        assertNotNull(result);
        assertEquals(returnsList, result);
    }

    @Test
    void getReturnsById_validReturnsId_shouldReturnReturnsDTO() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();
        when(returnsService.fetchReturnsById(returnsId)).thenReturn(returnsDTO);

        ResponseEntity<Object> responseEntity = returnsController.getReturnsbyId(returnsId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(returnsDTO, responseEntity.getBody());
    }

    @Test
    void getReturnsById_returnsNotFound_shouldReturnNotFoundStatus() {

        long returnsId = 1L;
        when(returnsService.fetchReturnsById(returnsId)).thenThrow(ReturnsNotFoundException.class);

        ResponseEntity<Object> responseEntity = returnsController.getReturnsbyId(returnsId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void addReturns_validReturns_shouldReturnCreatedStatus() {

        Returns returns = new Returns();
        when(returnsService.addReturn(any())).thenReturn("Returns added successfully");

        ResponseEntity<String> responseEntity = returnsController.addReturns(returns);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Returns added successfully", responseEntity.getBody());
    }

    @Test
    void addReturns_exceptionThrown_shouldReturnBadRequestStatus() {

        Returns returns = new Returns();
        when(returnsService.addReturn(any())).thenThrow(RuntimeException.class);


        ResponseEntity<String> responseEntity = returnsController.addReturns(returns);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void updateReturnsById_validReturnsId_shouldReturnOkStatus() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();
        when(returnsService.updateReturns(returnsId, returnsDTO)).thenReturn(returnsDTO);

        ResponseEntity<Object> responseEntity = returnsController.updateReturnsById(returnsId, returnsDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(returnsDTO, responseEntity.getBody());
    }

    @Test
    void updateReturnsById_returnsNotFound_shouldReturnNotFoundStatus() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();
        when(returnsService.updateReturns(returnsId, returnsDTO)).thenThrow(ReturnsNotFoundException.class);

        ResponseEntity<Object> responseEntity = returnsController.updateReturnsById(returnsId, returnsDTO);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateReturnsById_exceptionThrown_shouldReturnInternalServerErrorStatus() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();
        when(returnsService.updateReturns(returnsId, returnsDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> responseEntity = returnsController.updateReturnsById(returnsId, returnsDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void deleteByReturnsId_validReturnsId_shouldReturnOkStatus() {

        long returnsId = 1L;
        when(returnsService.deleteById(returnsId)).thenReturn("Returns deleted successfully");

        ResponseEntity<String> responseEntity = returnsController.deleteByReturnsId(returnsId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Returns deleted successfully", responseEntity.getBody());
    }

    @Test
    void testDeleteByReturnId_ReturnsNotFoundException() {

        long returnId = 1L;
        when(returnsService.deleteById(eq(returnId))).thenThrow(new ReturnsNotFoundException("Returns not found"));

        ResponseEntity<String> response = returnsController.deleteByReturnsId(returnId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Corrected status check
        assertEquals("Returns not found", response.getBody());

        verify(returnsService).deleteById(eq(returnId));
    }



}
