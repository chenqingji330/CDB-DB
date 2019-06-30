package com.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.exception.ServiceException;
import com.db.sys.pojo.SysRole;
import com.db.sys.service.SysRoleService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.PageObject;

@Controller
@RequestMapping("/role")
public class SysRoleController {
	
	
	@Autowired
	private SysRoleService sysRoleServcie;
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doRoleListUI.do
	@RequestMapping("/doRoleListUI")
	public String doRoleListUI() {
		
		return "sys/role_list";
		
	}
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doFindPageObjects.do?pageCurrent=1  分页查询角色数据
	
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
public SysResult doFindPageObjects(Integer pageCurrent,String username) {
		try {
			
			PageObject<SysRole> data=sysRoleServcie.doFindPageObjects(pageCurrent,username);
			
			return new SysResult(data);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中!!");
			
		}
		
	
}

	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doDeleteObject.do
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public SysResult  doDeleteObject(Integer id) {
		
		try {
			
			sysRoleServcie.doDeleteObject(id);
			return new SysResult();
					 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中!");
			
		}
		
		
	}
	
	
	
	
	
	
	
	
}








