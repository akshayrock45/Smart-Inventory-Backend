package com.incedo.capstone.smartinventory.controller.tests;

import com.incedo.capstone.smartinventory.controllers.ProductsController;
import com.incedo.capstone.smartinventory.dto.ProductsDTO;
import com.incedo.capstone.smartinventory.entities.Products;
import com.incedo.capstone.smartinventory.exceptions.ProductsNotFoundException;
import com.incedo.capstone.smartinventory.services.ProductsService;
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
public class ProductsControllerTest {

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsController productsController;

    @Test
    void testAddProducts_Successful() {
        Products products = new Products();
        when(productsService.addProduct(any(Products.class))).thenReturn(new Products());

        ResponseEntity<Products> response = productsController.addProducts(products);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(productsService).addProduct(any(Products.class));
    }

    @Test
    void testGetAllProducts_Successful() {
        when(productsService.getAllProducts()).thenReturn(Collections.singletonList(new ProductsDTO()));

        List<ProductsDTO> result = productsController.getAllProducts();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(productsService).getAllProducts();
    }

    @Test
    void testGetProductById_Successful() {
        long productId = 1L;
        when(productsService.getProductById(eq(productId))).thenReturn(new ProductsDTO());

        ResponseEntity<Object> response = productsController.getProductbyId(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof ProductsDTO);

        verify(productsService).getProductById(eq(productId));
    }

    @Test
    void testGetProductById_ProductsNotFoundException() {
        long productId = 1L;
        when(productsService.getProductById(eq(productId))).thenThrow(new ProductsNotFoundException("Products not found"));

        ResponseEntity<Object> response = productsController.getProductbyId(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Products not found", response.getBody());

        verify(productsService).getProductById(eq(productId));
    }

    @Test
    void testUpdatedProductsById_Successful() {
        long productId = 1L;
        ProductsDTO productsDTO = new ProductsDTO();
        when(productsService.updateProductsById(eq(productId), any(ProductsDTO.class))).thenReturn(new ProductsDTO());

        ResponseEntity<Object> response = productsController.updatedProductsById(productId, productsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof ProductsDTO);

        verify(productsService).updateProductsById(eq(productId), any(ProductsDTO.class));
    }

    @Test
    void testUpdatedProductsById_ProductsNotFoundException() {
        long productId = 1L;
        ProductsDTO productsDTO = new ProductsDTO();
        when(productsService.updateProductsById(eq(productId), any(ProductsDTO.class))).thenThrow(new ProductsNotFoundException("Products not found"));

        ResponseEntity<Object> response = productsController.updatedProductsById(productId, productsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Products not found", response.getBody());

        verify(productsService).updateProductsById(eq(productId), any(ProductsDTO.class));
    }

    @Test
    void testUpdatedProductsById_InternalServerError() {
        long productId = 1L;
        ProductsDTO productsDTO = new ProductsDTO();
        when(productsService.updateProductsById(eq(productId), any(ProductsDTO.class))).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<Object> response = productsController.updatedProductsById(productId, productsDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the Products.", response.getBody());

        verify(productsService).updateProductsById(eq(productId), any(ProductsDTO.class));
    }

    @Test
    void testDeleteByProductId_Successful() {
        long productId = 1L;
        when(productsService.deleteProductById(eq(productId))).thenReturn("Products Deleted Successfully");

        ResponseEntity<String> response = productsController.deleteByProductId(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Products Deleted Successfully", response.getBody());

        verify(productsService).deleteProductById(eq(productId));
    }

    @Test
    void testDeleteByProductId_ProductsNotFoundException() {
        long productId = 1L;
        when(productsService.deleteProductById(eq(productId))).thenThrow(new ProductsNotFoundException("Products not found"));

        ResponseEntity<String> response = productsController.deleteByProductId(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Products not found", response.getBody());

        verify(productsService).deleteProductById(eq(productId));
    }
}
