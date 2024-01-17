package com.incedo.capstone.smartinventory.entity.tests;

import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.entities.Products;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InwardsTest {

    @BeforeAll
    public static void TestStartMessage() {
        System.out.println("Test Starting at : " + new Date());
    }



        @Test
        public void testDefaultConstructor() {
            Inwards inwards = new Inwards();

            long inwardsId = inwards.getInwardsId();
            String nameOfTheSupplier = inwards.getNameOfTheSupplier();
            String dateOfSupply = inwards.getDateOfSupply();
            String itemName = inwards.getItemName();
            long invoiceNo = inwards.getInvoiceNo();
            int quantity = inwards.getQuantity();
            String receivedBy = inwards.getReceivedBy();
            long receiptNo = inwards.getReceiptNo();
            String billCheckedBy = inwards.getBillCheckedBy();
            Godowns godowns = inwards.getGodowns();
            List<Products> productsToPurchase = inwards.getProductsToPurchase();

            assertEquals(0, inwardsId);
            assertNull(nameOfTheSupplier);
            assertNull(dateOfSupply);
            assertNull(itemName);
            assertTrue(invoiceNo > 0);
            assertEquals(0, quantity);
            assertNull(receivedBy);
            assertTrue(receiptNo > 0);
            assertNull(billCheckedBy);
            assertNull(godowns);
            assertNull(productsToPurchase);
        }

        @Test
        public void testSetterAndGetters() {
            Inwards inwards = new Inwards();
            long inwardsId = 1;
            String nameOfTheSupplier = "Supplier A";
            String dateOfSupply = "2024-01-17";
            String itemName = "Item A";
            long invoiceNo = 12345;
            int quantity = 100;
            String receivedBy = "John Doe";
            long receiptNo = 67890;
            String billCheckedBy = "Jane Doe";
            Godowns godowns = new Godowns();
            List<Products> productsToPurchase = new ArrayList<>();

            inwards.setInwardsId(inwardsId);
            inwards.setNameOfTheSupplier(nameOfTheSupplier);
            inwards.setDateOfSupply(dateOfSupply);
            inwards.setItemName(itemName);
            inwards.setInvoiceNo(invoiceNo);
            inwards.setQuantity(quantity);
            inwards.setReceivedBy(receivedBy);
            inwards.setReceiptNo(receiptNo);
            inwards.setBillCheckedBy(billCheckedBy);
            inwards.setGodowns(godowns);
            inwards.setProductsToPurchase(productsToPurchase);

            // Assert
            assertEquals(inwardsId, inwards.getInwardsId());
            assertEquals(nameOfTheSupplier, inwards.getNameOfTheSupplier());
            assertEquals(dateOfSupply, inwards.getDateOfSupply());
            assertEquals(itemName, inwards.getItemName());
            assertEquals(invoiceNo, inwards.getInvoiceNo());
            assertEquals(quantity, inwards.getQuantity());
            assertEquals(receivedBy, inwards.getReceivedBy());
            assertEquals(receiptNo, inwards.getReceiptNo());
            assertEquals(billCheckedBy, inwards.getBillCheckedBy());
            assertEquals(godowns, inwards.getGodowns());
            assertEquals(productsToPurchase, inwards.getProductsToPurchase());
        }

        @Test
        public void testToString() {
            Inwards inwards = new Inwards();
            inwards.setInwardsId(1);
            inwards.setNameOfTheSupplier("Supplier A");
            inwards.setDateOfSupply("2024-01-17");
            inwards.setItemName("Item A");
            inwards.setInvoiceNo(12345);
            inwards.setQuantity(100);
            inwards.setReceivedBy("John Doe");
            inwards.setReceiptNo(67890);
            inwards.setBillCheckedBy("Jane Doe");
            Godowns godowns = new Godowns();
            inwards.setGodowns(godowns);
            List<Products> productsToPurchase = new ArrayList<>();
            inwards.setProductsToPurchase(productsToPurchase);

            String toStringResult = inwards.toString();

            assertTrue(toStringResult.contains("InwardsId=1"));
            assertTrue(toStringResult.contains("NameOfTheSupplier='Supplier A'"));
            assertTrue(toStringResult.contains("DateOfSupply='2024-01-17'"));
            assertTrue(toStringResult.contains("ItemName='Item A'"));
            assertTrue(toStringResult.contains("InvoiceNo=12345"));
            assertTrue(toStringResult.contains("quantity=100"));
            assertTrue(toStringResult.contains("receivedBy='John Doe'"));
            assertTrue(toStringResult.contains("ReceiptNo=67890"));
            assertTrue(toStringResult.contains("BillCheckedBy='Jane Doe'"));
            assertTrue(toStringResult.contains("godowns=" + godowns.toString()));
            assertTrue(toStringResult.contains("productsToPurchase=" + productsToPurchase.toString()));
        }
    }



