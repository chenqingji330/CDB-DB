package com.db.sys.service;

import java.util.List;

import com.db.sys.pojo.SysUser;
import com.db.sys.vo.PageObject;

public interface SysUserService {

	PageObject<SysUser> doFindPageObjects(String username, Integer pageCurrent);

}
