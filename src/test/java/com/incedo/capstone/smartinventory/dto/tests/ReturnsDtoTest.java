package com.incedo.capstone.smartinventory.dto.tests;

import com.incedo.capstone.smartinventory.dto.ReturnsDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnsDtoTest {
    @BeforeAll
    public static void returnsDtoTestBeginMessage(){
        System.out.println("Returns DTO Test Started at : "+ new Date());

    }
    @AfterAll
    public static void returnsDtoTestEndMessage(){
        System.out.println("Returns DTO Test Ended at : "+ new Date());

    }

    @Test
    public void testGettersSetters() {
        ReturnsDTO returnsDTO = new ReturnsDTO();
        returnsDTO.setInvoiceNo(1L);
        returnsDTO.setItemName("Pantene");
        returnsDTO.setQuantity(50.00);
        returnsDTO.setDateOfDelivery("12-10-2024");
        returnsDTO.setDateOfReturn("13-10-2024");
        returnsDTO.setReceiptNo(1);
        returnsDTO.setReceivedBy("Vishal");
        returnsDTO.setBillValue("100");
        returnsDTO.setBillCheckedBy("Vishal");

        assertEquals(1L,returnsDTO.getInvoiceNo());
        assertEquals("Pantene", returnsDTO.getItemName());
        assertEquals(50.00, returnsDTO.getQuantity());
        assertEquals("12-10-2024", returnsDTO.getDateOfDelivery());
        assertEquals("13-10-2024", returnsDTO.getDateOfReturn());
        assertEquals(1,returnsDTO.getReceiptNo());
        assertEquals("Vishal",returnsDTO.getReceivedBy());
        assertEquals("100",returnsDTO.getBillValue());
        assertEquals("Vishal",returnsDTO.getBillCheckedBy());
    }
}