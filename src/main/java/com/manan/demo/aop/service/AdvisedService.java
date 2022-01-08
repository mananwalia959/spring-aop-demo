package com.manan.demo.aop.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.manan.demo.aop.aspect.annotation.LogException;
import com.manan.demo.aop.aspect.annotation.LogExecutionTime;

@Service
public class AdvisedService {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdvisedService.class); 
	
	@LogExecutionTime
	public boolean advisedMethod() {
		System.out.println("advisedMethod");
		// introducing timeout for testing @LogExecutionTime
		sleep1sec();
		return true;
	}

	@LogExecutionTime
	public boolean nestedAdvisedMethod() {
		System.out.println("nestedAdvisedMethod");
		return this.advisedMethod();
	}
	
	@LogExecutionTime
	@LogException()
	public void generateException () {
		System.out.println("generate exception");
		throw new RuntimeException("exception generation test");
	}
	
	
	
	private void sleep1sec() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			LOGGER.error("interrupted", e);
		}
	}
	
	
	
}
