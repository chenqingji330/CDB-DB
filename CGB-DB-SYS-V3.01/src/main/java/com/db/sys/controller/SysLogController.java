package com.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.sys.pojo.SysLog;
import com.db.sys.service.SysLogService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.PageObject;


@Controller
@RequestMapping("/log")
public class SysLogController {
	
	@Autowired
	private SysLogService  sysLogService;
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/log/doLogListUI.do
	//页面展现
	@RequestMapping("/doLogListUI")
	public String doLogListUI() {
		
		return "sys/log_list";
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/log/doFindPageObjects.do?pageCurrent=1
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public SysResult doFindPageObjects(String username,Integer pageCurrent) {
		try {
	
		PageObject<SysLog> data=sysLogService.doFindPageObjects(username,pageCurrent);
			return new SysResult(data);
			
		} catch (Exception e) {
		  e.printStackTrace();
		  return new SysResult(e);
		}
		
	}	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/log/doDeleteObjects.do
	@RequestMapping("/doDeleteObjects")
	@ResponseBody
	public SysResult doDeleteObjects(@RequestParam("idArray") Integer...ids) {
		try {
			
			int rows = sysLogService.doDeleteObjects(ids);
			return new SysResult("删除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(e);
		
		}
		
	}
	
	
	
	
	

}
