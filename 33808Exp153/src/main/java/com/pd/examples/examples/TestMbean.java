package com.pd.examples.examples;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Random;

@ManagedResource(
        objectName="PD:category=MBeans,name=testBean",
        description="Managed Bean",
        log=true, logFile="jmx-platform.log")
@Component("testMbean")
public class TestMbean {


	private String message = "Simple Message";
	
	public TestMbean(){
		System.out.println("......TestMbean........");
	}

	@ManagedAttribute
	public String getMessage() {
		int returnValue = new Random().nextInt(20000);
		return String.valueOf(returnValue);
	}


	@ManagedOperation
	public void resetMessageViaMBean(){
		this.message = "Message RESET";
	}
	
	public void show(){
		System.out.println(message);
	}

}
