package com.manan.demo.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.manan.demo.aop.aspect.annotation.LogExecutionTime;


@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class); 
	
	@Around("@annotation(com.manan.demo.aop.aspect.annotation.LogExecutionTime) && execution(* *(..))")
	@Order(Ordered.LOWEST_PRECEDENCE) // should happen at last
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Signature signature = joinPoint.getSignature();
		
		long start = System.nanoTime();
		try {
			return joinPoint.proceed();
		} finally {
			long diff = System.nanoTime() - start;
			LOGGER.info("method {} in class completed in {} ns or {} ms", signature , diff , diff/1000000 );
		}
	}
	
	@AfterThrowing(pointcut = "@annotation(com.manan.demo.aop.aspect.annotation.LogException)" , throwing = "e")
	public void exception(JoinPoint joinPoint,Exception e) {		
		LOGGER.error("caught exception while executing method {} , exception {} , exception cause is {} " ,joinPoint.getSignature() , e.getClass() , e.getMessage());
	}
	

}
