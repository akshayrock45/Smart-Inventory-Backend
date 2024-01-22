package com.incedo.capstone.smartinventory.controller.tests;

import com.incedo.capstone.smartinventory.controllers.InwardsController;
import com.incedo.capstone.smartinventory.dto.InwardsDTO;
import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.exceptions.InwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.InwardsNotFoundException;
import com.incedo.capstone.smartinventory.services.InwardsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InwardsControllerTest {

    @Mock
    private InwardsService inwardsService;

    @InjectMocks
    private InwardsController inwardsController;

    @Test
    void testAddInwards_Successful() {
        Inwards inwards = new Inwards();
        when(inwardsService.addInwards(any(Inwards.class))).thenReturn("Inwards Added");

        ResponseEntity<String> response = inwardsController.addInwards(inwards);

        assertNotNull(response);
        assertEquals("Inwards Added", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(inwardsService).addInwards(any(Inwards.class));
    }

    @Test
    void testUpdateInwardsById_Successful() {
        long inwardsId = 1L;
        InwardsDTO inwardsDTO = new InwardsDTO();
        when(inwardsService.updateInwards(eq(inwardsId), any(InwardsDTO.class))).thenReturn(new InwardsDTO());

        ResponseEntity<Object> response = inwardsController.updateInwardsById(inwardsId, inwardsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof InwardsDTO);

        verify(inwardsService).updateInwards(eq(inwardsId), any(InwardsDTO.class));
    }

    @Test
    void testUpdateInwardsById_InwardsNotFoundException() {
        long inwardsId = 1L;
        InwardsDTO inwardsDTO = new InwardsDTO();
        when(inwardsService.updateInwards(eq(inwardsId), any(InwardsDTO.class))).thenThrow(new InwardsNotFoundException("Inwards not found"));

        ResponseEntity<Object> response = inwardsController.updateInwardsById(inwardsId, inwardsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Inwards not found", response.getBody());

        verify(inwardsService).updateInwards(eq(inwardsId), any(InwardsDTO.class));
    }

    @Test
    void testUpdateInwardsById_InternalServerError() {
        long inwardsId = 1L;
        InwardsDTO inwardsDTO = new InwardsDTO();
        when(inwardsService.updateInwards(eq(inwardsId), any(InwardsDTO.class))).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<Object> response = inwardsController.updateInwardsById(inwardsId, inwardsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the Inwards.", response.getBody());

        verify(inwardsService).updateInwards(eq(inwardsId), any(InwardsDTO.class));
    }

    @Test
    void testFetchInwards_Successful() {
        when(inwardsService.fetchInwards()).thenReturn(Collections.singletonList(new InwardsDTO()));

        List<InwardsDTO> result = inwardsController.fetchInwards();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(inwardsService).fetchInwards();
    }

    @Test
    void testGetInwardsById_Successful() {
        long inwardsId = 1L;
        when(inwardsService.fetchInwardsById(eq(inwardsId))).thenReturn(new InwardsDTO());

        ResponseEntity<Object> response = inwardsController.getInwardsbyId(inwardsId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof InwardsDTO);

        verify(inwardsService).fetchInwardsById(eq(inwardsId));
    }

    @Test
    void testGetInwardsById_InwardsNotFoundException() {
        long inwardsId = 1L;
        when(inwardsService.fetchInwardsById(eq(inwardsId))).thenThrow(new InwardsNotFoundException("Inwards not found"));

        ResponseEntity<Object> response = inwardsController.getInwardsbyId(inwardsId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Inwards not found", response.getBody());

        verify(inwardsService).fetchInwardsById(eq(inwardsId));
    }

    @Test
    void testDeleteByInwardsId_Successful() {
        long inwardsId = 1L;
        when(inwardsService.deleteById(eq(inwardsId))).thenReturn("Inwards Deleted Successfully");

        ResponseEntity<String> response = inwardsController.deleteByInwardsId(inwardsId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inwards Deleted Successfully", response.getBody());

        verify(inwardsService).deleteById(eq(inwardsId));
    }

    @Test
    void testDeleteByInwardsId_InwardsNotFoundException() {
        long inwardsId = 1L;
        when(inwardsService.deleteById(eq(inwardsId))).thenThrow(new InwardsNotFoundException("Inwards not found"));

        ResponseEntity<String> response = inwardsController.deleteByInwardsId(inwardsId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inwards not found", response.getBody());

        verify(inwardsService).deleteById(eq(inwardsId));
    }
}
