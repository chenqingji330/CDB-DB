package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.pojo.SysUser;

public interface SysUserDao {

	List<SysUser> doFindPageObjects(
			@Param("startIndex") int startIndex,
			@Param("pageSize")int pageSize, 
			@Param("username")String username);

	int getrowCount(@Param("username")String username);

}
