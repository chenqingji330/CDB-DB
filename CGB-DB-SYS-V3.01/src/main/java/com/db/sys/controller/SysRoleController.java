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
import com.db.sys.vo.CheckBox;
import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysRoleMenuVo;

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
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doRoleEditUI.do  跳转编辑页面
	@RequestMapping("/doRoleEditUI")
	public String doRoleEditUI() {
		
		return "sys/role_edit";
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doCheck.do  检查角色名称是否重复
	@RequestMapping("/doCheck")
	@ResponseBody
	public SysResult  doCheck(String name) {
		
			boolean flag=sysRoleServcie.doCheck(name);
		if(flag) 
			return new SysResult("该角色重复!").setState(0);
			
		else return new SysResult();
	
	
		
	}

	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doSaveObject.do  新增角色信息
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public SysResult  doSaveObject(SysRole role, Integer...menuIds) {
		try {
			System.out.println(role);
			int rows=sysRoleServcie.doSaveObject(role,menuIds);
			return new SysResult();
				
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中");
			 
			 
		
		}
		
	
		
		
		
	
		
	}
	
//	Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doDeleteObject.do按照id删除角色信息
		@RequestMapping("/doDeleteObject")
		@ResponseBody
		public SysResult doDeleteObject(Long id) {
			
			int rows=sysRoleServcie.doDeleteObject(id);
		
			
			return new SysResult();
			
		}
		
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doFindObjectById.do?id=47  按照id查询角色菜单vo信息
		@RequestMapping("/doFindObjectById")
		@ResponseBody
	public SysResult doFindObjectById(Integer id) {
			
			SysRoleMenuVo obj=sysRoleServcie.doFindObjectById(id);
			return new SysResult(obj);
					
			
		
	}
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doUpdateObject.do  修改角色信息
		@RequestMapping("/doUpdateObject")
		@ResponseBody
		public SysResult  doUpdateObject(SysRole role,Integer...menuIds) {
			int rows=sysRoleServcie.doUpdateObject(role,menuIds);
			return new SysResult();

			
		}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/role/doFindRoles.do 查找角色
		@RequestMapping("/doFindRoles")
		@ResponseBody
	public SysResult doFindRoles() {
			List<CheckBox>list=sysRoleServcie.doFindRoles(); 
		
			return new SysResult(list);
			
			
			
			
		
	}
	
	
	
	
	
	

}
