package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.pojo.SysRole;

public interface SysRoleDao {

	List<SysRole> doFindPageObjects(
			@Param("name") String username, 
			@Param("startIndex")long startIndex, 
			@Param("pageSize")int pageSize);

	int getRowCount(@Param("name") String name);

	int doDeleteObject(@Param("id") Integer id);

	
	
}
