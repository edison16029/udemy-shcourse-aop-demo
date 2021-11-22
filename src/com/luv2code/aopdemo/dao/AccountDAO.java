package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": Doing my DB Work: Adding an account");
	}
	
	public void doWork() {
		System.out.println(getClass() + ": Doing my DB Work");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	public List<Account> findAccounts(boolean tripWire) {
		
		if (tripWire) {
			throw new RuntimeException("Throwing some error");
		}
		
		System.out.println("RETURNING LIST OF ACCOUNTS");
		List<Account> myAccounts = new ArrayList<Account>();
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Manny", "Gold");
		Account temp3 = new Account("Alba", "Broze");
		
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}
	
}
