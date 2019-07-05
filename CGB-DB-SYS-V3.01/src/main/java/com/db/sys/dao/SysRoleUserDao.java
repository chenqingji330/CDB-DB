package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleUserDao {

	void doSaveObject(
			@Param("userId") Long id, 
			@Param("roleIds") Integer[] roleIds);

	List<Integer> findRoleIdsByUserId(Long id);

}
