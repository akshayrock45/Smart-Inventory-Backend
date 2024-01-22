package com.incedo.capstone.smartinventory.entity.tests;


import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.entities.Products;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OutwardsTest {

    @Test
    public void testDefaultConstructor() {
        Outwards outwards = new Outwards();
        Long outwardsId = outwards.getOutwardsId();
        String itemName = outwards.getItemName();
        long invoiceNo = outwards.getInvoiceNo();
        String dateOfSupply = outwards.getDateOfSupply();
        String dateOfDelivery = outwards.getDateOfDelivery();
        String deliveredTo = outwards.getDeliveredTo();
        int quantity = outwards.getQuantity();
        String purpose = outwards.getPurpose();
        long receiptNo = outwards.getReceiptNo();
        String billValue = outwards.getBillValue();
        String billCheckedBy = outwards.getBillCheckedBy();
        Godowns godowns = outwards.getGodowns();
        List<Products> productsToDeliver = outwards.getProductsToDeliver();

        assertNull(outwardsId);
        assertNull(itemName);
        assertTrue(invoiceNo > 0);
        assertNull(dateOfSupply);
        assertNull(dateOfDelivery);
        assertNull(deliveredTo);
        assertEquals(0, quantity);
        assertNull(purpose);
        assertTrue(receiptNo > 0);
        assertNull(billValue);
        assertNull(billCheckedBy);
        assertNull(godowns);
        assertNull(productsToDeliver);
    }

    @Test
    public void testSetterAndGetters() {
        Outwards outwards = new Outwards();
        Long outwardsId = 1L;
        String itemName = "Item A";
        long invoiceNo = 12345;
        String dateOfSupply = "2024-01-17";
        String dateOfDelivery = "2024-01-18";
        String deliveredTo = "Customer A";
        int quantity = 50;
        String purpose = "Sale";
        long receiptNo = 67890;
        String billValue = "1000";
        String billCheckedBy = "Jane Doe";
        Godowns godowns = new Godowns();
        List<Products> productsToDeliver = new ArrayList<>();


        outwards.setOutwardsId(outwardsId);
        outwards.setItemName(itemName);
        outwards.setInvoiceNo(invoiceNo);
        outwards.setDateOfSupply(dateOfSupply);
        outwards.setDateOfDelivery(dateOfDelivery);
        outwards.setDeliveredTo(deliveredTo);
        outwards.setQuantity(quantity);
        outwards.setPurpose(purpose);
        outwards.setReceiptNo(receiptNo);
        outwards.setBillValue(billValue);
        outwards.setBillCheckedBy(billCheckedBy);
        outwards.setGodowns(godowns);
        outwards.setProductsToDeliver(productsToDeliver);

        assertEquals(outwardsId, outwards.getOutwardsId());
        assertEquals(itemName, outwards.getItemName());
        assertEquals(invoiceNo, outwards.getInvoiceNo());
        assertEquals(dateOfSupply, outwards.getDateOfSupply());
        assertEquals(dateOfDelivery, outwards.getDateOfDelivery());
        assertEquals(deliveredTo, outwards.getDeliveredTo());
        assertEquals(quantity, outwards.getQuantity());
        assertEquals(purpose, outwards.getPurpose());
        assertEquals(receiptNo, outwards.getReceiptNo());
        assertEquals(billValue, outwards.getBillValue());
        assertEquals(billCheckedBy, outwards.getBillCheckedBy());
        assertEquals(godowns, outwards.getGodowns());
        assertEquals(productsToDeliver, outwards.getProductsToDeliver());
    }

    @Test
    public void testToString() {
        Outwards outwards = new Outwards();
        outwards.setOutwardsId(1L);
        outwards.setItemName("Item A");
        outwards.setInvoiceNo(12345);
        outwards.setDateOfSupply("2024-01-17");
        outwards.setDateOfDelivery("2024-01-18");
        outwards.setDeliveredTo("Customer A");
        outwards.setQuantity(50);
        outwards.setPurpose("Sale");
        outwards.setReceiptNo(67890);
        outwards.setBillValue("1000");
        outwards.setBillCheckedBy("Jane Doe");
        Godowns godowns = new Godowns();
        outwards.setGodowns(godowns);
        List<Products> productsToDeliver = new ArrayList<>();
        outwards.setProductsToDeliver(productsToDeliver);
        String toStringResult = outwards.toString();

        assertTrue(toStringResult.contains("OutwardsId=1"));
        assertTrue(toStringResult.contains("ItemName='Item A'"));
        assertTrue(toStringResult.contains("InvoiceNo=12345"));
        assertTrue(toStringResult.contains("DateOfSupply='2024-01-17'"));
        assertTrue(toStringResult.contains("DateOfDelivery='2024-01-18'"));
        assertTrue(toStringResult.contains("DeliveredTo='Customer A'"));
        assertTrue(toStringResult.contains("quantity=50"));
        assertTrue(toStringResult.contains("Purpose='Sale'"));
        assertTrue(toStringResult.contains("ReceiptNo=67890"));
        assertTrue(toStringResult.contains("BillValue='1000'"));
        assertTrue(toStringResult.contains("BillCheckedBy='Jane Doe'"));
        assertTrue(toStringResult.contains("godowns=" + godowns.toString()));
        assertTrue(toStringResult.contains("productsToDeliver=" + productsToDeliver.toString()));
    }
}

