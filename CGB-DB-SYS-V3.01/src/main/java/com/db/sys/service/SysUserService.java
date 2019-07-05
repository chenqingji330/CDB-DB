package com.db.sys.service;

import java.util.List;
import java.util.Map;

import com.db.sys.vo.PageObject;
import com.db.sys.vo.SysUserDeptVo;

public interface SysUserService {

	PageObject<SysUserDeptVo> doFindPageObjects(String username, Integer pageCurrent);

	int doSaveObject(SysUserDeptVo user, Integer[] roleIds);

	Map<String, Object> doFindObjectById(Long id);

}
