package com.db.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysMenuDao;
import com.db.sys.pojo.SysMenu;
import com.db.sys.vo.ZtreeNode;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<Map<String, Object>> doFindObjects() {
		List<Map<String, Object>> list =sysMenuDao.doFindObjects();
		if(list==null||list.size()==0) throw new ServiceException("数据不存在");
		return list;
				
	
	}

	@Override
	@Transactional
	public void doDeleteObject(Long id) {
		//校验id值
		if(id==null||id<1) {
			throw new ServiceException("请先选择需要删除的菜单");
			
		}
		int rows=sysMenuDao.doDeleteObject(id);
		if(rows<1) {
			throw new ServiceException("菜单不存在,请核对一下");
		}
		
	}

	@Override
	public List<ZtreeNode> doFindZtreeMenuNodes() {
		List<ZtreeNode> list=sysMenuDao.doFindZtreeMenuNodes();
		return list;
	}

	@Override
	@Transactional
	public int doSaveObject(SysMenu menu) {
		//校验数据
		if(menu==null) {
			throw new ServiceException("请核对 您输入的信息");
			
		}
		Date date = new Date();
		menu.setCreatedUser("chenqingji").setModifiedUser("chenqingji");
		
		
		int rows=sysMenuDao.doSaveObject(menu);
	
		return rows;
	}

	@Override
	@Transactional
	public int doUpdateObject(SysMenu menu) {
		if(menu==null) {
			throw new ServiceException("请核对好再提交,信息不能为空");	
		}
		String name = menu.getName();
		if(StringUtils.isEmpty(name)) {
			throw new ServiceException("菜单名称不能为空");	
		}
		Long parentId = menu.getParentId();
		if(parentId==null||parentId<1) {
			throw new ServiceException("请先选择上上级菜单");
			
		}
		int rows=sysMenuDao.doUpdateObject(menu);
		return rows;
	}

}
