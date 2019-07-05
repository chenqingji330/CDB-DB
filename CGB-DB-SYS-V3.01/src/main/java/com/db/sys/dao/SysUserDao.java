package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.sys.vo.SysUserDeptVo;



public interface SysUserDao {

	List<SysUserDeptVo> doFindPageObjects(
			@Param("startIndex") int startIndex,
			@Param("pageSize")int pageSize, 
			@Param("username")String username);

	int getrowCount(@Param("username")String username);

	int doSaveObject(SysUserDeptVo user);

	SysUserDeptVo doFindObjectById(Long id);

}
