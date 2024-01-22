package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.InwardsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.entities.Products;
import com.incedo.capstone.smartinventory.exceptions.InwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.InwardsNotFoundException;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import com.incedo.capstone.smartinventory.repository.InwardsRepository;
import com.incedo.capstone.smartinventory.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InwardsServiceTest {

    @Mock
    private InwardsRepository inwardsRepository;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private GodownsRepository godownsRepository;

    @InjectMocks
    private InwardsService inwardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configure mock behavior if needed
    }


    @Test
    void testAddInwards_Failure() {

        Inwards inwards = new Inwards();

        when(inwardsRepository.save(any(Inwards.class))).thenReturn(null);

        assertThrows(InwardsCreationException.class, () -> inwardsService.addInwards(inwards));
        verify(inwardsRepository, times(1)).save(inwards);
        verifyNoMoreInteractions(productsRepository, godownsRepository);
    }

    @Test
    void testUpdateInwards_Success() {

        long inwardsId = 1L;
        InwardsDTO inwardsDto = new InwardsDTO();
        Inwards existingInwards = new Inwards();

        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.of(existingInwards));
        when(inwardsRepository.save(any(Inwards.class))).thenReturn(existingInwards);

        InwardsDTO result = inwardsService.updateInwards(inwardsId, inwardsDto);

        assertNotNull(result);
        verify(inwardsRepository, times(1)).findById(inwardsId);
        verify(inwardsRepository, times(1)).save(existingInwards);
    }

    @Test
    void testUpdateInwards_NotFound() {

        long inwardsId = 1L;
        InwardsDTO inwardsDto = new InwardsDTO();

        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(InwardsNotFoundException.class, () -> inwardsService.updateInwards(inwardsId, inwardsDto));
        verify(inwardsRepository, times(1)).findById(inwardsId);
        verifyNoMoreInteractions(inwardsRepository);
    }

    @Test
    void testFetchInwards() {

        List<Inwards> inwardsList = new ArrayList<>();
        when(inwardsRepository.findAll()).thenReturn(inwardsList);

        List<InwardsDTO> result = inwardsService.fetchInwards();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(inwardsRepository, times(1)).findAll();
    }

    @Test
    void testFetchInwardsById_Success() {

        long inwardsId = 1L;
        Inwards existingInwards = new Inwards();

        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.of(existingInwards));

        InwardsDTO result = inwardsService.fetchInwardsById(inwardsId);

        assertNotNull(result);
        verify(inwardsRepository, times(1)).findById(inwardsId);
    }

    @Test
    void testFetchInwardsById_NotFound() {

        long inwardsId = 1L;

        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(InwardsNotFoundException.class, () -> inwardsService.fetchInwardsById(inwardsId));
        verify(inwardsRepository, times(1)).findById(inwardsId);
    }

    @Test
    void testDeleteById_Success() {

        long inwardsId = 1L;
        Inwards deleteInwards = new Inwards();
        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.of(deleteInwards));

        InwardsNotFoundException exception = assertThrows(InwardsNotFoundException.class, () -> {
            inwardsService.deleteById(inwardsId);
        });

        assertEquals("Inwards not found for id: " + inwardsId, exception.getMessage());
        verify(inwardsRepository, times(1)).findById(inwardsId);
        verify(inwardsRepository, times(1)).delete(deleteInwards);
    }


    @Test
    void testDeleteById_NotFound() {

        long inwardsId = 1L;

        when(inwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(InwardsNotFoundException.class, () -> inwardsService.deleteById(inwardsId));
        verify(inwardsRepository, times(1)).findById(inwardsId);
        verifyNoMoreInteractions(inwardsRepository);
    }
}
