package com.db.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.sys.dao.SysUserDao;
import com.db.sys.pojo.SysUser;
import com.db.sys.util.PageUtils;
import com.db.sys.vo.PageObject;
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageObject<SysUser> doFindPageObjects(String username, Integer pageCurrent) {
		
		
		int pageSize=5;
		int startIndex=(pageCurrent-1)*pageSize;
	
		List<SysUser> list=sysUserDao.doFindPageObjects(startIndex,pageSize,username);
		int rowCount=sysUserDao.getrowCount(username);
		PageObject<SysUser> vo = PageUtils.newInstance(pageCurrent, rowCount, pageSize, list);
		
		return vo;
	}

}
