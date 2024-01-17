package com.incedo.capstone.smartinventory.entity.tests;

import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.entities.Returns;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReturnsTest {

    @Test
    public void testDefaultConstructor() {
        Returns returns = new Returns();
        long returnId = returns.getReturnid();
        String itemName = returns.getItemName();
        Double quantity = returns.getQuantity();
        String dateOfDelivery = returns.getDateOfDelivery();
        String dateOfReturn = returns.getDateOfReturn();
        long receiptNo = returns.getReceiptNo();
        String receivedBy = returns.getReceivedBy();
        String billValue = returns.getBillValue();
        String billCheckedBy = returns.getBillCheckedBy();
        boolean isDamaged = returns.getIsDamaged();
        Godowns godowns = returns.getGodowns();
        Outwards outwards = returns.getOutwards();

        assertEquals(0, returnId);
        assertNull(itemName);
        assertNull(quantity);
        assertNull(dateOfDelivery);
        assertNull(dateOfReturn);
        assertTrue(receiptNo > 0);
        assertNull(receivedBy);
        assertNull(billValue);
        assertNull(billCheckedBy);
        assertFalse(isDamaged);
        assertNull(godowns);
        assertNull(outwards);
    }

    @Test
    public void testSetterAndGetters() {
        Returns returns = new Returns();
        long returnId = 1L;
        String itemName = "Item A";
        Double quantity = 10.5;
        String dateOfDelivery = "2024-01-17";
        String dateOfReturn = "2024-01-18";
        long receiptNo = 67890;
        String receivedBy = "John Doe";
        String billValue = "500";
        String billCheckedBy = "Jane Doe";
        boolean isDamaged = true;
        Godowns godowns = new Godowns();
        Outwards outwards = new Outwards();

        returns.setReturnid(returnId);
        returns.setItemName(itemName);
        returns.setQuantity(quantity);
        returns.setDateOfDelivery(dateOfDelivery);
        returns.setDateOfReturn(dateOfReturn);
        returns.setReceiptNo(receiptNo);
        returns.setReceivedBy(receivedBy);
        returns.setBillValue(billValue);
        returns.setBillCheckedBy(billCheckedBy);
        returns.setIsDamaged(isDamaged);
        returns.setGodowns(godowns);
        returns.setOutwards(outwards);

        assertEquals(returnId, returns.getReturnid());
        assertEquals(itemName, returns.getItemName());
        assertEquals(quantity, returns.getQuantity());
        assertEquals(dateOfDelivery, returns.getDateOfDelivery());
        assertEquals(dateOfReturn, returns.getDateOfReturn());
        assertEquals(receiptNo, returns.getReceiptNo());
        assertEquals(receivedBy, returns.getReceivedBy());
        assertEquals(billValue, returns.getBillValue());
        assertEquals(billCheckedBy, returns.getBillCheckedBy());
        assertEquals(isDamaged, returns.getIsDamaged());
        assertEquals(godowns, returns.getGodowns());
        assertEquals(outwards, returns.getOutwards());
    }

    @Test
    public void testToString() {
        Returns returns = new Returns();
        returns.setReturnid(1L);
        returns.setItemName("Item A");
        returns.setQuantity(10.5);
        returns.setDateOfDelivery("2024-01-17");
        returns.setDateOfReturn("2024-01-18");
        returns.setReceiptNo(67890);
        returns.setReceivedBy("John Doe");
        returns.setBillValue("500");
        returns.setBillCheckedBy("Jane Doe");
        returns.setIsDamaged(true);
        Godowns godowns = new Godowns();
        returns.setGodowns(godowns);
        Outwards outwards = new Outwards();
        returns.setOutwards(outwards);

        String toStringResult = returns.toString();

        assertTrue(toStringResult.contains("Returnid=1"));
        assertTrue(toStringResult.contains("ItemName='Item A'"));
        assertTrue(toStringResult.contains("quantity=10.5"));
        assertTrue(toStringResult.contains("DateOfDelivery='2024-01-17'"));
        assertTrue(toStringResult.contains("DateOfReturn='2024-01-18'"));
        assertTrue(toStringResult.contains("ReceiptNo=67890"));
        assertTrue(toStringResult.contains("ReceivedBy='John Doe'"));
        assertTrue(toStringResult.contains("BillValue='500'"));
        assertTrue(toStringResult.contains("BillCheckedBy='Jane Doe'"));
        assertTrue(toStringResult.contains("IsDamaged=true"));
        assertTrue(toStringResult.contains("godowns=" + godowns.toString()));
        assertTrue(toStringResult.contains("outwards=" + outwards.toString()));
    }
}
