package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.pojo.SysRole;
import com.db.sys.vo.CheckBox;

public interface SysRoleDao {

	List<SysRole> doFindPageObjects(
			@Param("name") String username, 
			@Param("startIndex")long startIndex, 
			@Param("pageSize")int pageSize);

	int getRowCount(@Param("name") String name);

	int getCount(@Param("name")String name);



	int insertObject(SysRole role);

	SysRole findRoleIdByName(String name);

	void doDeleteObject(Long id);

	SysRole doFindObjectById(Integer id);

	int doUpdateObject(SysRole role);

	List<CheckBox> doFindRoles();



	
	
}
