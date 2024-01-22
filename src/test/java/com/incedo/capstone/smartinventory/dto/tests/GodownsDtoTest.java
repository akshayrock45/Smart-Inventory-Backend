package com.incedo.capstone.smartinventory.dto.tests;

import com.incedo.capstone.smartinventory.dto.GodownsDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GodownsDtoTest {
    @BeforeAll
    public static void godownsDtoTestBeginMessage(){
        System.out.println("Godowns DTO Test Started at : "+ new Date());

    }
    @AfterAll
    public static void godownsDtoTestEndMessage(){
        System.out.println("Godowns DTO Test Ended at : "+ new Date());

    }

    @Test
    public void testGettersSetters() {
        GodownsDTO godownsDTO = new GodownsDTO();
        godownsDTO.setGodownId(1L);
        godownsDTO.setLocation("Chennai");
        godownsDTO.setCapacityInQuintals(1000.00);
        godownsDTO.setStartDate(LocalDate.now());
        godownsDTO.setStatus(true);


        assertEquals(1L, godownsDTO.getGodownId());
        assertEquals("Chennai", godownsDTO.getLocation());
        assertEquals(1000.00, godownsDTO.getCapacityInQuintals());
        assertEquals(LocalDate.now(), godownsDTO.getStartDate());
        assertEquals(true, godownsDTO.getStatus());

    }
}