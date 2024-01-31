package com.incedo.capstone.smartinventory;

import com.incedo.capstone.smartinventory.controller.tests.UsersControllerTest;
import com.incedo.capstone.smartinventory.dto.tests.UsersDtoTest;
import com.incedo.capstone.smartinventory.entity.tests.UsersTest;
import com.incedo.capstone.smartinventory.entity.tests.GodownsTest;
import com.incedo.capstone.smartinventory.entity.tests.InwardsTest;
import com.incedo.capstone.smartinventory.entity.tests.OutwardsTest;
import com.incedo.capstone.smartinventory.entity.tests.ProductsTest;
import com.incedo.capstone.smartinventory.entity.tests.ReturnsTest;
import com.incedo.capstone.smartinventory.services.UsersServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SmartInventoryApplicationTests {

	@BeforeEach
	void setUp() {
	}

	@Test
	void testUsersGettersSetters() {
		UsersTest usersTest = new UsersTest();
		usersTest.testGettersSettersPassing();
		usersTest.testGettersSettersFailing();
	}

	@Test
	void testUsersDtoGettersSetters() {
		UsersDtoTest usersDtoTest = new UsersDtoTest();
		usersDtoTest.testGettersSetters();
	}


	@Test
	void testGodownsEntity() {
		GodownsTest godownsTest = new GodownsTest();
		godownsTest.testGettersSettersPassing();
		godownsTest.testDefaultConstructor();
		godownsTest.testToString();
	}

	@Test
	void testInwardsEntity() {
		InwardsTest inwardsTest = new InwardsTest();
		inwardsTest.testDefaultConstructor();
		inwardsTest.testSetterAndGetters();
		inwardsTest.testToString();
	}

	@Test
	void testOutwardsEntity() {
		OutwardsTest outwardsTest = new OutwardsTest();
		outwardsTest.testDefaultConstructor();
		outwardsTest.testSetterAndGetters();
		outwardsTest.testToString();
	}

	@Test
	void testProductsEntity() {
		ProductsTest productsTest = new ProductsTest();
		productsTest.testDefaultConstructor();
		productsTest.testSetterAndGetters();
		productsTest.testToString();
	}

	@Test
	void testReturnsEntity() {
		ReturnsTest returnsTest = new ReturnsTest();
		returnsTest.testDefaultConstructor();
		returnsTest.testSetterAndGetters();
		returnsTest.testToString();
	}

}
