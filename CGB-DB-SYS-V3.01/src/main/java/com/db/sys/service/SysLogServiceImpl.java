package com.db.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysLogDao;
import com.db.sys.pojo.SysLog;
import com.db.sys.vo.PageObject;

@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public PageObject<SysLog> doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<SysLog> pageObject = new PageObject<SysLog>();
		Integer rowCount=sysLogDao.selectCount(username);
		if(rowCount<1) {throw new ServiceException("数据库不存在数据");}
		//分页查询
		int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		Integer pageCount=rowCount/pageSize+1;
		List<SysLog> list=sysLogDao.findLogsList(username,startIndex,pageSize);
		pageObject.setPageCount(pageCount).setPageCurrent(pageCurrent).setRecords(list).setRowCount(rowCount).setPageSize(pageSize);
		
		
		return pageObject;
	}

	@Override
	public int doDeleteObjects(Integer[] ids) {
		
		if(ids==null||ids.length==0) {
			throw new ServiceException("请先选择需要删除的数据");
			
		}
		int rows=0;
		try {
			rows=sysLogDao.deleteByIds(ids);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统维护中");
		}
		if(rows==0) 
			throw new ServiceException("记录不存在,可能已经删除了");
		
		return rows;
	}

}
