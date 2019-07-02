package com.db.sys.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {

	int saveObject(@Param("menuIds") Integer[] menuIds, @Param("roleId") Long id);

	void doDeleteObject(Long id);

	List<Integer> doFindObjectById(Integer id);



	
	
}
