package com.db.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		
		return  "starterAll";
		//我是陈清吉!!	你是谁??大家好	
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/doPageUI.do
	@RequestMapping("doPageUI")
	public String doPageUI() {
		
		return "common/page";
		
	}

}
