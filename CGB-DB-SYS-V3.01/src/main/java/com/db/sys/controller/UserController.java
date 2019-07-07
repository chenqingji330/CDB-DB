package com.db.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.sys.pojo.SysUser;
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
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doUpdateObject.do
	//需要返回数据类型是:SysRsult 
	
//	username: admin123456
//	email: admin@t.cn
//	mobile: 13624356789   接收的数据
//	deptId: 7
//	roleIds: 1
//	id: 1
	
	
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public SysResult doUpdateObject(SysUser user,Integer...roleIds) {
		sysUserService.doUpdateObject(user,roleIds);
		return new SysResult("更新成功!");
	}
	
	
//	id: 1
//	valid: 0  传递的值
	//Request URL: http://localhost:8888/CGB-DB-SYS-V3.01/user/doValidById.do  禁用启用功能
	@RequestMapping("/doValidById")
	@ResponseBody
	public SysResult doValidById(Long id,Long valid) {
		
		sysUserService.doValidById(id,valid);
		
		return  new SysResult("更新成功!");
	}

	 @RequestMapping("/doLogin")
	   @ResponseBody
	   public SysResult doLogin(String username,
			   String password,boolean rememberMe) {
		   //提交用户信息到业务层
		   //1.获取主题对象(Subject)
		   Subject subject=SecurityUtils.getSubject();
		   //2.提交信息(提交给shiro的securityManager)
		   UsernamePasswordToken token=
		   new UsernamePasswordToken(username, password);
		   System.out.println("rememberMe="+rememberMe);
		   if(rememberMe) {
			  token.setRememberMe(true);
		   } 
		   subject.login(token);//执行登录认证
		   return new SysResult("login ok");
	   }

}
