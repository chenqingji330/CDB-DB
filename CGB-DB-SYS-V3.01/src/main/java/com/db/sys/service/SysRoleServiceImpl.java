package com.db.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysRoleDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.pojo.SysRole;
import com.db.sys.util.PageUtils;
import com.db.sys.vo.CheckBox;
import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysRoleMenuVo;
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public PageObject<SysRole> doFindPageObjects(Integer pageCurrent,String username) {

		int rowCount=sysRoleDao.getRowCount(username);
		int pageSize=5;
		long startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> list=sysRoleDao.doFindPageObjects(username,startIndex,pageSize);
		
		return PageUtils.newInstance(pageCurrent, rowCount, pageSize, list);
	}
 
	@Override
	public boolean doCheck(String name) {
		int rowCount=sysRoleDao.getCount(name);
		
		return rowCount==0?false:true;
	}

	@Override
	@Transactional
	public int doSaveObject(SysRole role, Integer[] menuIds) {
	// chekcbox封装了角色名name,角色描述note,菜单menu_id
		Date date = new Date(); 
		//将角色信息存到角色表中sys_roles
		
		role.setCreatedTime(date).setModifiedTime(date).setCreatedUser("chenqingji").setModifiedUser("chenqingji");
		int rows=sysRoleDao.insertObject(role);
		//保存角色菜单关系表
		Long id =sysRoleDao.findRoleIdByName(role.getName()).getId();
		System.out.println(id);
		int row=sysRoleMenuDao.saveObject(menuIds,id);
		
		return 0;
	}

	@Override
	public int doDeleteObject(Long id) {
		//1删除role表
		//2删除role_menu表
		sysRoleDao.doDeleteObject(id);
		sysRoleMenuDao.doDeleteObject(id);
		
		
		
		return 0;
	}
	
	@Override
	public SysRoleMenuVo doFindObjectById(Integer id) {
		SysRoleMenuVo vo = new  SysRoleMenuVo();
		SysRole role=sysRoleDao.doFindObjectById(id);
		vo.setName(role.getName()).setNote(role.getNote());
		List<Integer> list=sysRoleMenuDao.doFindObjectById(id);
		vo.setMenuIds(list);
		return vo;
	}

	
	
	@Override
	public int doUpdateObject(SysRole role, Integer[] menuIds) {
	//校验参数
		if(menuIds==null||menuIds.length<1) {
			throw new ServiceException("请给与角色授权");	
		}
		//1更新role角色表
		Date date = new Date();
		role.setModifiedTime(date).setModifiedUser("cheniqingji");
		int rows=sysRoleDao.doUpdateObject(role);
		//更新角色菜单表  先闪删除角色菜单关系表,再新增角色菜单信息表
		Long roleId = role.getId();
		sysRoleMenuDao.doDeleteObject(roleId);
		sysRoleMenuDao.saveObject(menuIds, roleId);
		
		
		
		
		return 0;
	}

	@Override
	public List<CheckBox> doFindRoles() {
		List<CheckBox>list=sysRoleDao.doFindRoles();
		return list;
	}
}
