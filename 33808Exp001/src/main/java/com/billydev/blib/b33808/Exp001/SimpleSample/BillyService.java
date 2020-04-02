package com.billydev.blib.b33808.Exp001.SimpleSample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service 
public class BillyService {

	@Value("Billy")
	private String name ; 
	
	public String getHelloMessage(){
		return "Hello "+ this.name; 
	}
	
}
