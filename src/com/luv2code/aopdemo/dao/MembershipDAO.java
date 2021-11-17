package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public int addSillyMember() {
		System.out.println(getClass() + ": Doing my DB Work: Adding a membership");
		return 0;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": Go to sleep");
	}
}
