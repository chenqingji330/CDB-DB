package com.db.sys.service;

import java.util.List;

import com.db.sys.pojo.SysLog;
import com.db.sys.vo.PageObject;


public interface SysLogService {

	PageObject<SysLog> doFindPageObjects(String username,Integer pageCurrent);

	int doDeleteObjects(Integer[] ids);

}
