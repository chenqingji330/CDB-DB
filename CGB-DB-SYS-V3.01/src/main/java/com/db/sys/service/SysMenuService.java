package com.db.sys.service;

import java.util.List;
import java.util.Map;

import com.db.sys.pojo.SysMenu;
import com.db.sys.vo.ZtreeNode;

public interface SysMenuService {

	List<Map<String, Object>> doFindObjects();

	void doDeleteObject(Long id);

	List<ZtreeNode> doFindZtreeMenuNodes();

	int doSaveObject(SysMenu menu);

	int doUpdateObject(SysMenu menu);

}
