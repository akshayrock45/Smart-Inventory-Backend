package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.InwardsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.entities.Products;
import com.incedo.capstone.smartinventory.exceptions.InwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.InwardsNotFoundException;
import com.incedo.capstone.smartinventory.mapper.InwardsMapper;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import com.incedo.capstone.smartinventory.repository.InwardsRepository;
import com.incedo.capstone.smartinventory.repository.ProductsRepository;
import com.incedo.capstone.smartinventory.services.InwardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
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
    }
    @Test
    public void testAddInwards_Success() {
        Inwards inwards = new Inwards();
        inwards.setProductsToPurchase(Collections.singletonList(new Products()));
        inwards.setGodowns(new Godowns());

        when(inwardsRepository.save(any(Inwards.class))).thenReturn(inwards);

        assertEquals("Inwards Added", inwardsService.addInwards(inwards));

        // Verify that repository save was called
        verify(inwardsRepository, times(1)).save(any(Inwards.class));
    }

    @Test
    public void testAddInwards_InsufficientGodownCapacity() {
        Inwards inwards = new Inwards();
        inwards.setProductsToPurchase(Collections.singletonList(new Products()));
        inwards.setGodowns(new Godowns());

        when(inwardsRepository.save(any(Inwards.class))).thenReturn(inwards);
        when(productsRepository.findById(anyLong())).thenReturn(Optional.of(new Products()));
        when(godownsRepository.findById(anyLong())).thenReturn(Optional.of(new Godowns()));

        assertThrows(InwardsCreationException.class, () -> inwardsService.addInwards(inwards));

        // Verify that repository save was not called
        verify(inwardsRepository, never()).save(any(Inwards.class));
    }


    @Test
    public void testUpdateInwards_Success() {
        long inwardsId = 1L;
        InwardsDTO inwardsDTO = new InwardsDTO();

        when(inwardsRepository.findById(inwardsId)).thenReturn(Optional.of(new Inwards()));
        when(inwardsRepository.save(any(Inwards.class))).thenReturn(new Inwards());

        assertNotNull(inwardsService.updateInwards(inwardsId, inwardsDTO));

        // Verify that repository findById and save were called
        verify(inwardsRepository, times(1)).findById(inwardsId);
        verify(inwardsRepository, times(1)).save(any(Inwards.class));
    }

    @Test
    public void testUpdateInwards_NotFound() {
        long inwardsId = 1L;
        InwardsDTO inwardsDTO = new InwardsDTO();

        when(inwardsRepository.findById(inwardsId)).thenReturn(Optional.empty());

        assertThrows(InwardsNotFoundException.class, () -> inwardsService.updateInwards(inwardsId, inwardsDTO));

        // Verify that repository findById was called
        verify(inwardsRepository, times(1)).findById(inwardsId);
        // Verify that repository save was not called
        verify(inwardsRepository, never()).save(any(Inwards.class));
    }

    // Add more tests as needed...
}
