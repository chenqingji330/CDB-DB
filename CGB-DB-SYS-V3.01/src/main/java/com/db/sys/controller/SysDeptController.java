package com.db.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.sys.pojo.SysDept;
import com.db.sys.service.SysDeptService;
import com.db.sys.util.SysResult;
import com.db.sys.vo.ZtreeNode;

@Controller
@RequestMapping("/dept")
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptServcie;
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doDeptListUI.do
	
	@RequestMapping("/doDeptListUI")
	public String doDeptListUI() {
		
		return "sys/dept_list";
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doFindObjects.do  //查询部门列表
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public SysResult doFindObjects() {
		List<Map<String,Object>> list=sysDeptServcie.doFindObjects();
		return new SysResult(list);
		
	}
	

	
//	Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doDeleteObject.do   删除部门
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public SysResult  doDeleteObject(Long id) {
		int rows=sysDeptServcie.doDeleteObject(id);
		return new SysResult();
		
	
		
	}
	
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doDeptEditUI.do  //跳转编辑页面
	
	@RequestMapping("/doDeptEditUI")
	public String doDeptEditUI() {
		
		return "sys/dept_edit";
		
	}
	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doFindZTreeNodes.do  查询树形结构
	@RequestMapping("/doFindZTreeNodes")
	@ResponseBody
	public SysResult doFindZTreeNodes() {
		
		List<ZtreeNode> list=sysDeptServcie.doFindZTreeNodes();
		return new SysResult(list);
		
	}
//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doSaveObject.do  保存新增数据
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public SysResult doSaveObject(SysDept dept) {
		try {
			
			sysDeptServcie.doSaveObject(dept);
			return new SysResult();
			
		} catch (Exception e) {
		e.printStackTrace();
		return new SysResult("提交失败");
				 
		}
		
	}

	
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/dept/doUpdateObject.do  修改部门信息
	
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public SysResult  doUpdateObject(SysDept dept) {
		 try {
			 
			 sysDeptServcie.doUpdateObject(dept);
			 return new SysResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult("系统维护中!!");
					 
		}
		
		
	}
	
	
	
	
	





}


