package com.hpmont.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hpmont.util.HttpClientUtil;

public class LoginControllerTest {

	@Test
	public void appLoginTest(){
		
		Map<String,String> requsetMap = new HashMap<String,String>();
        String URL = "http://localhost:8080/education/app/login.jhtml";
        
        requsetMap.put("userName", "admin");
        requsetMap.put("password", "123456");
        
		HttpClientUtil.post(requsetMap, URL);
	}
	
}
