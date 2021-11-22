package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class MyDemoLoggingAspect {
	// this is where we add all of our related advice for logging
//	@Before("execution(public void addAccount())")
//	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//	@Before("execution(public void add*())")
//	@Before("execution(* add*())")
//	@Before("execution(* add*(com.luv2code.aopdemo.Account))")
//	@Before("execution(* add*(Account))") //This throws error. need to give fully qualified account
//	@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
//	@Before("execution(* add*(..))")
//	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") //All class, all method, all params
//	@Before("forDaoPackage()")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n========>>> Logging some data");
	}
}
