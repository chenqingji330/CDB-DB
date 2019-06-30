package com.db.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.exception.ServiceException;
import com.db.sys.pojo.SysMenu;
import com.db.sys.service.SysMenuService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.ZtreeNode;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private SysMenuService sysMenuServcie;
	 
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doMenuListUI.do  //跳转菜单页面
	@RequestMapping("/doMenuListUI")
	public String  doMenuListUI() {
		
		return "sys/menu_list";
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doFindObjects.do  查询菜单列表信息
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public SysResult doFindObjects() {
		
		try {
			
			List<Map<String,Object>> list=sysMenuServcie.doFindObjects();
			System.out.println(list);
			return new  SysResult(list);
				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(e);
		
		}
		
	
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doDeleteObject.do
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public SysResult doDeleteObject(Long id) {
		try {
			sysMenuServcie.doDeleteObject(id);
			return new SysResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中,请稍后重试!");
		}
		
		
	}

	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doMenuEditUI.do
	@RequestMapping("/doMenuEditUI")
	public String doMenuEditUI() {
		return "sys/menu_edit";
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doFindZtreeMenuNodes.do  //树形图
	@RequestMapping("/doFindZtreeMenuNodes")
	@ResponseBody
	public SysResult doFindZtreeMenuNodes(){
		try {
			
			
			List<ZtreeNode> list=sysMenuServcie.doFindZtreeMenuNodes();
			System.out.println(list);
			return new SysResult(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("对不起,系统正在维护中!")
		;
		}
		
	}
	
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/menu/doSaveObject.do
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public SysResult doSaveObject(SysMenu menu) {
	try {
		int rows=sysMenuServcie.doSaveObject(menu);
		return new SysResult();
	} catch (Exception e) {
	e.printStackTrace();
	throw new ServiceException("系统正在抢救中");
	}
		
		
		
	}
	
	//http://localhost:8888/CGB-DB-SYS-V3.01/menu/doUpdateObject.do  //修改菜单数据
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public SysResult doUpdateObject(SysMenu menu) {
		try {
			int rows=sysMenuServcie.doUpdateObject(menu);
			return new SysResult();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中!!");
			
		}
		
	}
	
	
	
	
	
}
