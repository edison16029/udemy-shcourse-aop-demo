package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}

	// this is where we add all of our related advices for logging
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
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n========>>> Executing @Before advice()");
	}
	
//	@Before("forDaoPackage()")
//	public void performApiAnalytics() {
//		System.out.println("========>>> Executing @Before advice number two!!");
//	}
	

}
