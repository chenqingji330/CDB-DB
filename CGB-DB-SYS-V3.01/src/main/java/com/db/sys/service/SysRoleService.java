package com.db.sys.service;

import java.util.List;

import com.db.sys.pojo.SysRole;
import com.db.sys.vo.PageObject;

public interface SysRoleService {

	PageObject<SysRole>doFindPageObjects(Integer pageCurrent,String username);

	void doDeleteObject(Integer id);

	

}
