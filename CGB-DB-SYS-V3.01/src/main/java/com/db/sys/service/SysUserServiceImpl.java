package com.db.sys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.sys.dao.SysRoleUserDao;
import com.db.sys.dao.SysUserDao;
import com.db.sys.pojo.SysUser;
import com.db.sys.util.PageUtils;
import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysUserDeptVo;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysRoleUserDao sysRoleUserDao;

	@Override
	public PageObject<SysUserDeptVo> doFindPageObjects(String username, Integer pageCurrent) {
		
		
		int pageSize=5;
		int startIndex=(pageCurrent-1)*pageSize;
	
		List<SysUserDeptVo> list=sysUserDao.doFindPageObjects(startIndex,pageSize,username);
		int rowCount=sysUserDao.getrowCount(username);
		PageObject<SysUserDeptVo> vo = PageUtils.newInstance(pageCurrent, rowCount, pageSize, list);
		
		return vo;
	}
@Override
public int doSaveObject(SysUserDeptVo user, Integer[] roleIds) {
	Date date = new Date();
	//给用户密码加密
	String password = user.getPassword();
	//创建随机盐值
	String salt = UUID.randomUUID().toString();
   SimpleHash sh = new SimpleHash("MD5", password, salt,1);
   String Md5Pass = sh.toHex();
   user.setCreatedTime(date).setModifiedTime(date).setCreatedUser("chenqingji").setModifiedUser("chenqingji");
   user.setPassword(Md5Pass).setSalt(salt);
   System.out.println(user);
   int rows
   =sysUserDao.doSaveObject(user);
   
   
   //更新角色用户表
   Long id = user.getId();
   sysRoleUserDao.doSaveObject(id,roleIds);
   
   
	return 0;
}
@Override
public Map<String, Object> doFindObjectById(Long id) {
	SysUserDeptVo user=sysUserDao.doFindObjectById(id);
	//3.基于id查询用户对应的角色id
	List<Integer> roleIds=sysRoleUserDao.findRoleIdsByUserId(id);
	//4.对信息进行封装
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("user", user);
	map.put("roleIds", roleIds);
	return map;
}
@Override
public void doUpdateObject(SysUser user, Integer[] roleIds) {
	//1更新用户信息表
	System.out.println(user);
	Date date = new Date();
	user.setModifiedTime(date);
	
//	Subject token = SecurityUtils.getSubject();
//	SysUser user1 = (SysUser)token.getPrincipals();
//	String username = user1.getUsername();
//	user.setModifiedUser(username);
	sysUserDao.doUpdateObject(user);
	//3.保存用户和角色关系数据
	Long id = user.getId();
	//3.1先删除关系数据,按照用户id删除角色用户关系表
	sysRoleUserDao.deleteRoleUserById(id);
	//3.2再添加新的关系数据  按照用户id和角色id更新关系表
	sysRoleUserDao.doSaveObject(id, roleIds);
}
}
