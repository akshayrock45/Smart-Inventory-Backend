package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.ReturnsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Returns;
import com.incedo.capstone.smartinventory.exceptions.GodownNotFoundException;
import com.incedo.capstone.smartinventory.exceptions.ReturnsNotFoundException;
import com.incedo.capstone.smartinventory.mapper.ReturnsMapper;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import com.incedo.capstone.smartinventory.repository.ReturnsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReturnsServiceTest {

    @Mock
    private ReturnsRepository returnsRepository;

    @Mock
    private GodownsRepository godownsRepository;

    @InjectMocks
    private ReturnsService returnsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchReturns() {

        List<Returns> returnsList = new ArrayList<>();
        when(returnsRepository.findAll()).thenReturn(returnsList);

        List<ReturnsDTO> result = returnsService.fetchReturns();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(returnsRepository, times(1)).findAll();
    }

    @Test
    void testFetchReturnsById_Success() {

        long returnsId = 1L;
        Returns existingReturns = new Returns();
        when(returnsRepository.findById(anyLong())).thenReturn(Optional.of(existingReturns));

        ReturnsDTO result = returnsService.fetchReturnsById(returnsId);

        assertNotNull(result);
        verify(returnsRepository, times(1)).findById(returnsId);
    }

    @Test
    void testFetchReturnsById_NotFound() {

        long returnsId = 1L;
        when(returnsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ReturnsNotFoundException.class, () -> returnsService.fetchReturnsById(returnsId));
        verify(returnsRepository, times(1)).findById(returnsId);
    }




    @Test
    void testAddReturn_DamagedProduct() {

        Returns returns = new Returns();
        returns.setIsDamaged(true);

        when(returnsRepository.save(any(Returns.class))).thenReturn(returns);

        String result = returnsService.addReturn(returns);

        verify(returnsRepository, times(1)).save(returns);
        verifyNoMoreInteractions(godownsRepository);

        assertEquals("Return added. Product is damaged, not updating Godown.", result);
    }
//    @Test
//    void testAddReturn_InsufficientCapacity() {
//        // Mock data
//        Returns returns = new Returns();
//        Godowns godown = mock(Godowns.class);
//
//        // Mock repository interactions
//        when(returnsRepository.save(any(Returns.class))).thenReturn(returns);
//        when(godownsRepository.findById(anyLong())).thenReturn(Optional.of(godown));
//
//        // Stub the behavior using doReturn() for a method call on a real object
//        doReturn(5.0).when(godown).getCapacityInQuintals();
//
//        // Set up a situation where the capacity is insufficient
//        returns.setQuantity(10.0); // setting a return with quantity more than current capacity
//
//        // Test and Verify
//        try {
//            returnsService.addReturn(returns);
//        } catch (Exception e) {
//            System.out.println("Exception thrown: " + e);
//            Assertions.assertEquals(ReturnsNotFoundException.class, e.getClass());
//            Assertions.assertEquals("Insufficient capacity in the return.", e.getMessage());
//        }
//
//        // Verify interactions
//        verify(returnsRepository, times(1)).save(returns);
//        verify(returnsRepository, times(1)).delete(any());
//        verify(godownsRepository, never()).save(any());
//    }

    @Test
    void testUpdateReturns_Success() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();
        Returns existingReturns = new Returns();

        when(returnsRepository.findById(anyLong())).thenReturn(Optional.of(existingReturns));
        when(returnsRepository.save(any(Returns.class))).thenReturn(existingReturns);

        ReturnsDTO result = returnsService.updateReturns(returnsId, returnsDTO);

        assertNotNull(result);
        verify(returnsRepository, times(1)).findById(returnsId);
        verify(returnsRepository, times(1)).save(existingReturns);
    }

    @Test
    void testUpdateReturns_NotFound() {

        long returnsId = 1L;
        ReturnsDTO returnsDTO = new ReturnsDTO();

        when(returnsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GodownNotFoundException.class, () -> returnsService.updateReturns(returnsId, returnsDTO));
        verify(returnsRepository, times(1)).findById(returnsId);
        verifyNoMoreInteractions(returnsRepository);
    }

    @Test
    void testDeleteById_Success() {

        long returnsId = 1L;
        Returns deleteReturns = new Returns();
        when(returnsRepository.findById(anyLong())).thenReturn(Optional.of(deleteReturns));

        assertThrows(ReturnsNotFoundException.class, () -> returnsService.deleteById(returnsId));

        verify(returnsRepository, times(1)).findById(returnsId);
        verify(returnsRepository, times(1)).delete(deleteReturns);
    }

    @Test
    void testDeleteById_NotFound() {

        long returnsId = 1L;
        when(returnsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ReturnsNotFoundException.class, () -> returnsService.deleteById(returnsId));
        verify(returnsRepository, times(1)).findById(returnsId);
        verifyNoMoreInteractions(returnsRepository);
    }
}
