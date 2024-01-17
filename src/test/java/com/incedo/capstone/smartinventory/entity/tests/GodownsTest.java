package com.incedo.capstone.smartinventory.entity.tests;

import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GodownsTest {
    @BeforeAll
    public static void TestStartMessage() {
        System.out.println("Test Starting at : " + new Date());
    }


    @Test
    public void testGettersSettersPassing(){

        Godowns godown=new Godowns();
        Users users = new Users();
        godown.setCapacityInQuintals(1000.0);
        godown.setGodownId(1);
        godown.setStatus(true);
        godown.setLocation("Chennai");
        godown.setStartDate(LocalDate.of(2024,1,12));
        godown.setUsers(users);


        assertEquals(1,godown.getGodownId());
        assertEquals(1000.0,godown.getCapacityInQuintals());
        assertEquals(1,godown.getGodownId());
        assertEquals(true,godown.getStatus());
        assertEquals(users,godown.getUsers());
        assertEquals("Chennai",godown.getLocation());

    }


        @Test
        public void testDefaultConstructor() {
            Godowns godown = new Godowns();

            assertEquals(0, godown.getGodownId());
            assertNull(godown.getLocation() );
            assertNull(godown.getCapacityInQuintals());
            assertNull(godown.getStartDate());
            assertTrue(godown.getStatus());
            assertNull(godown.getUsers());
        }



        @Test
        public void testToString() {
            Godowns godown = new Godowns();
            godown.setGodownId(1);
            godown.setLocation("Warehouse A");
            godown.setCapacityInQuintals(1000.0);
            godown.setStartDate(LocalDate.of(2024, 1, 17));
            godown.setStatus(false);
            Users users = new Users();
            godown.setUsers(users);

            String toStringResult = godown.toString();

            assertTrue(toStringResult.contains("godownId=1"));
            assertTrue(toStringResult.contains("location='Warehouse A'"));
            assertTrue(toStringResult.contains("capacityInQuintals=1000.0"));
            assertTrue(toStringResult.contains("startDate=2024-01-17"));
            assertFalse(toStringResult.contains("status=false"));
            assertTrue(toStringResult.contains("users=" + users.toString()));
        }
    }


