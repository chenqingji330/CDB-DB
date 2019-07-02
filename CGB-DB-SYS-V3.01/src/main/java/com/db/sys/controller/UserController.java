package com.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.sys.pojo.SysUser;
import com.db.sys.service.SysUserService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.PageObject;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private SysUserService sysUserService;
	
	
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doPwdEditUI.do  //跳转密码修改页面
	@RequestMapping("/doPwdEditUI")
	public String doPwdEditUI() {
		
		return "sys/pwd_edit";
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doUserListUI.do  跳转用户信息页面
	@RequestMapping("/doUserListUI")
	public String doUserListUI() {
		
		return "sys/user_list";
		
	}

	
//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doFindPageObjects.do?pageCurrent=1&username=  用户信息分页显示
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public SysResult  doFindPageObjects(	String username,Integer pageCurrent) {
		
		            PageObject<SysUser> list=sysUserService.doFindPageObjects(username,pageCurrent);
		            
		            return new  SysResult(list);
		
		
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doUserEditUI.do  跳转用户编辑页面
	@RequestMapping("/doUserEditUI")
	public String doUserEditUI() {
		
		return "sys/user_edit";
		
	}
	
	
	
	
	
	
	
	

}
