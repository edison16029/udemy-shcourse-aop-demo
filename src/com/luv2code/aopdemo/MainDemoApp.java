package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call the business method
		Account theAccount = new Account();
		theAccount.setName("Eddie");
		theAccount.setLevel("Gold");
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();
		
		theAccountDAO.setName("hello");
		theAccountDAO.setServiceCode("004");
		
		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();
		
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		//close the context
		
		context.close();

	}

}
