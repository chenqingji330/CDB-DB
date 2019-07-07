package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.pojo.SysLog;



public interface SysLogDao {
	int deleteByIds(@Param("ids") Integer... ids);

	Integer selectCount(@Param("username")String username);

	List<SysLog> findLogsList(
			@Param("username") String username,
			@Param("startIndex")int startIndex, 
			@Param("pageSize")int pageSize);

	void insertObject(SysLog log);

}
