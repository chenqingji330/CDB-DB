package com.db.sys.util;

import java.util.List;


import com.db.sys.vo.PageObject;

public abstract class PageUtils{
	
	public static <T>PageObject<T> newInstance(Integer pageCurrent, int rowCount, int pageSize, List<T> records) {
		PageObject<T> po=new PageObject<T>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount-1)/pageSize+1);
		return po;
	}
}