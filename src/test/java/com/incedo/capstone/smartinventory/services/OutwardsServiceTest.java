package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.OutwardsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.entities.Products;
import com.incedo.capstone.smartinventory.exceptions.OutwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.OutwardsNotFoundException;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import com.incedo.capstone.smartinventory.repository.OutwardsRepository;
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

public class OutwardsServiceTest {

    @Mock
    private OutwardsRepository outwardsRepository;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private GodownsRepository godownsRepository;

    @InjectMocks
    private OutwardsService outwardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void testAddOutwards_Failure() {

        Outwards outwards = new Outwards();

        when(outwardsRepository.save(any(Outwards.class))).thenReturn(null);

        assertThrows(OutwardsCreationException.class, () -> outwardsService.addOutwards(outwards));
        verify(outwardsRepository, times(1)).save(outwards);
        verifyNoMoreInteractions(productsRepository, godownsRepository);
    }

    @Test
    void testUpdateOutwards_Success() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDto = new OutwardsDTO();
        Outwards existingOutwards = new Outwards();

        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.of(existingOutwards));
        when(outwardsRepository.save(any(Outwards.class))).thenReturn(existingOutwards);

        OutwardsDTO result = outwardsService.updateById(outwardsId, outwardsDto);

        assertNotNull(result);
        verify(outwardsRepository, times(1)).findById(outwardsId);
        verify(outwardsRepository, times(1)).save(existingOutwards);
    }

    @Test
    void testUpdateOutwards_NotFound() {

        long outwardsId = 1L;
        OutwardsDTO outwardsDto = new OutwardsDTO();

        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OutwardsNotFoundException.class, () -> outwardsService.updateById(outwardsId, outwardsDto));
        verify(outwardsRepository, times(1)).findById(outwardsId);
        verifyNoMoreInteractions(outwardsRepository);
    }

    @Test
    void testFetchOutwards() {

        List<Outwards> outwardsList = new ArrayList<>();
        when(outwardsRepository.findAll()).thenReturn(outwardsList);

        List<OutwardsDTO> result = outwardsService.fetchOutwards();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(outwardsRepository, times(1)).findAll();
    }

    @Test
    void testFetchOutwardsById_Success() {

        long outwardsId = 1L;
        Outwards existingOutwards = new Outwards();

        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.of(existingOutwards));

        OutwardsDTO result = outwardsService.fetchById(outwardsId);

        assertNotNull(result);
        verify(outwardsRepository, times(1)).findById(outwardsId);
    }

    @Test
    void testFetchOutwardsById_NotFound() {

        long outwardsId = 1L;

        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OutwardsNotFoundException.class, () -> outwardsService.fetchById(outwardsId));
        verify(outwardsRepository, times(1)).findById(outwardsId);
    }

    @Test
    void testDeleteById_Success() {

        long outwardsId = 1L;
        Outwards deleteOutwards = new Outwards();
        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.of(deleteOutwards));

        OutwardsNotFoundException exception = assertThrows(OutwardsNotFoundException.class,()->{
            outwardsService.deleteById(outwardsId);
        });

        assertEquals("No records found for id: " + outwardsId, exception.getMessage());
        verify(outwardsRepository, times(1)).findById(outwardsId);
        verify(outwardsRepository, times(1)).delete(deleteOutwards);
    }


    @Test
    void testDeleteById_NotFound() {

        long outwardsId = 1L;

        when(outwardsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OutwardsNotFoundException.class, () -> outwardsService.deleteById(outwardsId));
        verify(outwardsRepository, times(1)).findById(outwardsId);
        verifyNoMoreInteractions(outwardsRepository);
    }
}
