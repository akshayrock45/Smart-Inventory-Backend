package com.incedo.capstone.smartinventory.dto.tests;

import com.incedo.capstone.smartinventory.dto.InwardsDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InwardsDtoTest {
    @BeforeAll
    public static void inwardsDtoTestBeginMessage(){
        System.out.println("Inwards DTO Test Started at : "+ new Date());

    }
    @AfterAll
    public static void inwardsDtoTestEndMessage(){
        System.out.println("Inwards DTO Test Ended at : "+ new Date());

    }

    @Test
    public void testGettersSetters() {
        InwardsDTO inwardsDTO = new InwardsDTO();
        inwardsDTO.setInwardsId(1L);
        inwardsDTO.setNameOfTheSupplier("Pantene");
        inwardsDTO.setDateOfSupply("12-10-2024");
        inwardsDTO.setItemName("Pantene");
        inwardsDTO.setInvoiceNo(1);
        inwardsDTO.setQuantity(100);
        inwardsDTO.setReceivedBy("Vasu");
        inwardsDTO.setReceiptNo(1);
        inwardsDTO.setBillCheckedBy("Vasu");


        assertEquals(1L, inwardsDTO.getInwardsId());
        assertEquals("Pantene", inwardsDTO.getNameOfTheSupplier());
        assertEquals("12-10-2024", inwardsDTO.getDateOfSupply());
        assertEquals("Pantene", inwardsDTO.getItemName());
        assertEquals(1, inwardsDTO.getInvoiceNo());
        assertEquals(100, inwardsDTO.getQuantity());
        assertEquals("Vasu", inwardsDTO.getReceivedBy());
        assertEquals(1, inwardsDTO.getReceiptNo());
        assertEquals("Vasu", inwardsDTO.getBillCheckedBy());
    }
}