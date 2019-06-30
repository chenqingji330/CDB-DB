package com.db.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysRoleDao;
import com.db.sys.pojo.SysRole;
import com.db.sys.util.PageUtils;
import com.db.sys.vo.PageObject;
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public PageObject<SysRole> doFindPageObjects(Integer pageCurrent,String username) {

		int rowCount=sysRoleDao.getRowCount(username);
		int pageSize=5;
		long startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> list=sysRoleDao.doFindPageObjects(username,startIndex,pageSize);
		
		return PageUtils.newInstance(pageCurrent, rowCount, pageSize, list);
	}

	@Override
	public void doDeleteObject(Integer id) {
		//判断id值
		if(id==null||id<1) {
			throw new ServiceException("请先选择需要删除的数据");
			
			
		}
		int rows=sysRoleDao.doDeleteObject(id);
		if(rows<1) {  throw new ServiceException("可能数据库已经删除了");
			
		}
		
		
	}

}
