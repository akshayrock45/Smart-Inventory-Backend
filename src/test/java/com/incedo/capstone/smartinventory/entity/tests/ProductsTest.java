package com.incedo.capstone.smartinventory.entity.tests;


import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.entities.Products;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest {

    @Test
    public void testDefaultConstructor() {
        Products products = new Products();
        long productId = products.getProductId();
        String productName = products.getProductName();
        double price = products.getPrice();
        int quantity = products.getQuantity();
        List<Inwards> inwards = products.getInwards();
        List<Outwards> outwards = products.getOutwards();

        assertEquals(0, productId);
        assertNull(productName);
        assertEquals(0.0, price);
        assertEquals(0, quantity);
        assertNull(inwards);
        assertNull(outwards);
    }

    @Test
    public void testSetterAndGetters() {
        Products products = new Products();
        long productId = 1L;
        String productName = "Product A";
        double price = 50.0;
        int quantity = 100;
        List<Inwards> inwards = List.of(new Inwards());
        List<Outwards> outwards = List.of(new Outwards());

        products.setProductId(productId);
        products.setProductName(productName);
        products.setPrice(price);
        products.setQuantity(quantity);
        products.setInwards(inwards);
        products.setOutwards(outwards);

        assertEquals(productId, products.getProductId());
        assertEquals(productName, products.getProductName());
        assertEquals(price, products.getPrice());
        assertEquals(quantity, products.getQuantity());
        assertEquals(inwards, products.getInwards());
        assertEquals(outwards, products.getOutwards());
    }

    @Test
    public void testToString() {
        Products products = new Products();
        products.setProductId(1L);
        products.setProductName("Product A");
        products.setPrice(50.0);
        products.setQuantity(100);
        products.setInwards(List.of(new Inwards()));
        products.setOutwards(List.of(new Outwards()));

        String toStringResult = products.toString();

        assertTrue(toStringResult.contains("productId=1"));
        assertTrue(toStringResult.contains("productName='Product A'"));
        assertTrue(toStringResult.contains("price=50.0"));
        assertTrue(toStringResult.contains("quantity=100"));
        assertTrue(toStringResult.contains("inwards=" + products.getInwards().toString()));
        assertTrue(toStringResult.contains("outwards=" + products.getOutwards().toString()));
    }
}
