package com.incedo.capstone.smartinventory.dto.tests;

import com.incedo.capstone.smartinventory.dto.OutwardsDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutwardsDtoTest {
    @BeforeAll
    public static void outwardsDtoTestBeginMessage(){
        System.out.println("Outwards DTO Test Started at : "+ new Date());

    }
    @AfterAll
    public static void outwardsDtoTestEndMessage(){
        System.out.println("Outwards DTO Test Ended at : "+ new Date());

    }

    @Test
    public void testGettersSetters() {
        OutwardsDTO outwardsDTO = new OutwardsDTO();
        outwardsDTO.setOutwardsId(1L);
        outwardsDTO.setItemName("Pantene");
        outwardsDTO.setInvoiceNo(1);
        outwardsDTO.setDateOfSupply("12-10-2024");
        outwardsDTO.setDateOfDelivery("13-10-2024");
        outwardsDTO.setDeliveredTo("Vishal");
        outwardsDTO.setQuantity(50);
        outwardsDTO.setPurpose("Sales");
        outwardsDTO.setReceiptNo(1);
        outwardsDTO.setBillValue("100");
        outwardsDTO.setBillCheckedBy("Vishal");


        assertEquals(1L, outwardsDTO.getOutwardsId());
        assertEquals("Pantene", outwardsDTO.getItemName());
        assertEquals(1, outwardsDTO.getInvoiceNo());
        assertEquals("12-10-2024", outwardsDTO.getDateOfSupply());
        assertEquals("13-10-2024", outwardsDTO.getDateOfDelivery());
        assertEquals(50, outwardsDTO.getQuantity());
        assertEquals("Sales", outwardsDTO.getPurpose());
        assertEquals(1,outwardsDTO.getReceiptNo());
        assertEquals("100",outwardsDTO.getBillValue());
        assertEquals("Vishal",outwardsDTO.getBillCheckedBy());
    }
}