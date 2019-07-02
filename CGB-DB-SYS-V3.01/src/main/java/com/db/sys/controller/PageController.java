package com.db.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/doIndexUI.do
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		
		return  "starterAll";
		
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/doPageUI.do
	@RequestMapping("doPageUI")
	public String doPageUI() {
		
		return "common/page";
		
	}
	
	


}
