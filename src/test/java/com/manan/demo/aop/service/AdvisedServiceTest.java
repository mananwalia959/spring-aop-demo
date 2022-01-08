package com.manan.demo.aop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdvisedServiceTest {
	
	@Autowired private AdvisedService advisedService;

	@Test
	void testAdvisedMethod() {
		assertTrue(advisedService.advisedMethod());
	}
	
	@Test
	void testNestedAdvisedMethod() {
		assertTrue(advisedService.nestedAdvisedMethod());
	}
	
	@Test
	void testGenerateException() {
		assertThrows(RuntimeException.class,advisedService::generateException);
	}
	

}
