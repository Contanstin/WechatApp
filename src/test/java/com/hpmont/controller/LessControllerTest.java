package com.hpmont.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hpmont.util.HttpClientUtil;

public class LessControllerTest {

	@Test
	public void appLoginTest(){
		
		Map<String,String> requsetMap = new HashMap<String,String>();
        String URL = "http://localhost:8080/education/line/lesson/arrange.jhtml";
        
        requsetMap.put("lessonId", "1");
        requsetMap.put("shopId", "2");
        
		HttpClientUtil.post(requsetMap, URL);
	}
	
}
