package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.db.sys.pojo.SysMenu;
import com.db.sys.vo.ZtreeNode;

public interface SysMenuDao {

	List<Map<String, Object>> doFindObjects();

	int doDeleteObject(Long id);

	List<ZtreeNode> doFindZtreeMenuNodes();

	int doSaveObject(SysMenu menu);

	int doUpdateObject(SysMenu menu);

	List<String> findPermissions(Integer[] array);

	
}
