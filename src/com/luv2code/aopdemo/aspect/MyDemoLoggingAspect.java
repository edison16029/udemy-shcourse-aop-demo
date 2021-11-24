package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

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
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdviceJoinPoint(JoinPoint theJoinPoint) {
		System.out.println("\n========>>> JoinPoint Aspect");
		//display the method signature
		
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method Signature " + methodSignature);
		
		//display the method arguments
		Object[] methodArgs = theJoinPoint.getArgs();
		
		for(Object tempArg : methodArgs) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				System.out.println("Account Name: " + theAccount.getName());
				System.out.println("Account Level: " + theAccount.getLevel());
			}
		}
	}
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		
		System.out.println("\n==========>>> Executing @AfterReturning on method : " + method);
		
		System.out.println("\n==========>>> Result is " + result);
		
		//We can post process data 
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n==========>>> Result is " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		// TODO Auto-generated method stub
		
		for(Account tempAccount : result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		String method = theJoinPoint.getSignature().toShortString();
		
		System.out.println("\n==========>>> Executing @AfterThrowing on method : " + method);
		
		System.out.println("\n==========>>> The exception is: " + theExc);
		
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		
		System.out.println("\n==========>>> Executing @After (finally) on method : " + method);
	}
	
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n==========>>> Executing @Around on method : " + method);
		
		long begin = System.currentTimeMillis();
		Object result = null;
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw e;
//			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		
		System.out.println("\n======> Duration: " + duration/1000.0 + " seconds" );
		
		return result; //Since we called the function, we need to return whatever the function returns
	}


}
