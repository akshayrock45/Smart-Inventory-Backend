package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.ProductsDTO;
import com.incedo.capstone.smartinventory.entities.Products;
import com.incedo.capstone.smartinventory.exceptions.ProductsNotFoundException;
import com.incedo.capstone.smartinventory.mapper.ProductsMapper;
import com.incedo.capstone.smartinventory.repository.ProductsRepository;
import com.incedo.capstone.smartinventory.services.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductsServiceTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById_Success() {
        long productId = 1L;

        when(productsRepository.findById(productId)).thenReturn(Optional.of(new Products()));

        assertNotNull(productsService.getProductById(productId));

        verify(productsRepository, times(1)).findById(productId);
    }

    @Test
    public  void testGetProductById_NotFound() {
        long productId = 1L;

        when(productsRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductsNotFoundException.class, () -> productsService.getProductById(productId));

        verify(productsRepository, times(1)).findById(productId);
    }

    @Test
    public void testUpdateProductsById_Success() {
        long productId = 1L;
        ProductsDTO productsDTO = new ProductsDTO();

        when(productsRepository.findById(productId)).thenReturn(Optional.of(new Products()));
        when(productsRepository.save(any(Products.class))).thenReturn(new Products());

        assertNotNull(productsService.updateProductsById(productId, productsDTO));

        verify(productsRepository, times(1)).findById(productId);
        verify(productsRepository, times(1)).save(any(Products.class));
    }

    @Test
    public void testUpdateProductsById_NotFound() {
        long productId = 1L;
        ProductsDTO productsDTO = new ProductsDTO();

        when(productsRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductsNotFoundException.class, () -> productsService.updateProductsById(productId, productsDTO));

        verify(productsRepository, times(1)).findById(productId);

        verify(productsRepository, never()).save(any(Products.class));
    }

}
