package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFortune() {
		
		// Simulate a delay
		try {
		TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "Expect heavy traffic this morning";
		
	}

	public String getFortune(boolean tripWire) {
		// TODO Auto-generated method stub
		if(tripWire) {
			throw new RuntimeException("Highway closed");
		}
		return getFortune();
	}
}
