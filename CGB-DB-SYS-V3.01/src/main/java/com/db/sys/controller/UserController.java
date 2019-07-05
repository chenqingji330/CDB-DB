package com.db.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.db.sys.service.SysUserService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysUserDeptVo;

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
		
		            PageObject<SysUserDeptVo> list=sysUserService.doFindPageObjects(username,pageCurrent);
		            
		            return new  SysResult(list);
		
		
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doUserEditUI.do  跳转用户编辑页面
	@RequestMapping("/doUserEditUI")
	public String doUserEditUI() {
		
		return "sys/user_edit";
		
	}
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doSaveObject.do  保存用户信息
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public SysResult  doSaveObject(SysUserDeptVo user,Integer...roleIds) {
		
		
		int rows=sysUserService.doSaveObject(user,roleIds);
	return new SysResult();
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doFindObjectById.do?id=1 按照id查询用户信息
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public SysResult doFindObjectById(Long id) {
		Map<String, Object> data=sysUserService.doFindObjectById(id);
		System.out.println(data);
		return new SysResult(data);
				
		
	}
	
	
	
	

}
