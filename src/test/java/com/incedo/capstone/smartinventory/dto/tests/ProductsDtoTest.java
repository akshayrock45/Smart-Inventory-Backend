package com.incedo.capstone.smartinventory.dto.tests;

import com.incedo.capstone.smartinventory.dto.ProductsDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsDtoTest {
    @BeforeAll
    public static void productsDtoTestBeginMessage(){
        System.out.println("Products DTO Test Started at : "+ new Date());

    }
    @AfterAll
    public static void productsDtoTestEndMessage(){
        System.out.println("Products DTO Test Ended at : "+ new Date());

    }

    @Test
    public void testGettersSetters() {
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProductId(1L);
        productsDTO.setProductName("Pantene");
        productsDTO.setPrice(50.00);
        productsDTO.setQuantity(100);


        assertEquals(1L, productsDTO.getProductId());
        assertEquals("Pantene", productsDTO.getProductName());
        assertEquals(50.00, productsDTO.getPrice());
        assertEquals(100, productsDTO.getQuantity());
    }
}