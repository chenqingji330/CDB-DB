package com.db.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysDeptDao;
import com.db.sys.pojo.SysDept;
import com.db.sys.vo.ZtreeNode;
@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptDao;

	@Override
	public List<Map<String, Object>> doFindObjects() {
		List<Map<String, Object>>  list=sysDeptDao.doFindObjects();
		return list;
	}

	@Override
	@Transactional
	public int doDeleteObject(Long id) {
		//判断id是否有效
		if(id==null||id<1) {
			throw new ServiceException("请先选择需要删除的id的部门");
			
		}
		int rows=sysDeptDao.doDeleteObject(id);
		if(rows==0) {
			throw new ServiceException("数据不存在了");
			
		}
		
		
		
		return rows;
	}

	@Override
	public List<ZtreeNode> doFindZTreeNodes() {
		List<ZtreeNode>list =sysDeptDao.doFindZTreeNodes();
		return list;
	}
@Override
@Transactional
public void doSaveObject(SysDept dept) {
	//校验数据
	if(dept==null) {
		throw new ServiceException("请输入正确的数据");
		
	}
	Date date = new Date();
	dept.setCreatedTime(date).setModifiedTime(date).setCreatedUser("chenqingji").setModifiedUser("chenqingji");
	sysDeptDao.doSaveObject(dept);
	
}

@Override
public void doUpdateObject(SysDept dept) {
	//校验参数
	if(dept==null) { throw new ServiceException("请核对后再提交!!");
		
	}
	Date date = new Date();
	dept.setModifiedTime(date).setModifiedUser("chenqingji");
	int rows=sysDeptDao.doUpdateObject(dept);
	
	if(rows<1) { throw new ServiceException("保存失败!");
		
	}
}
}
