package com.db.sys.service;

import java.util.List;

import com.db.sys.pojo.SysRole;
import com.db.sys.vo.CheckBox;
import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysRoleMenuVo;

public interface SysRoleService {

	PageObject<SysRole>doFindPageObjects(Integer pageCurrent,String username);

	boolean doCheck(String name);



	int doSaveObject(SysRole role, Integer[] menuIds);

	int doDeleteObject(Long id);

	SysRoleMenuVo doFindObjectById(Integer id);

	int doUpdateObject(SysRole role, Integer[] menuIds);

	List<CheckBox> doFindRoles();

	

}
