package com.db.sys.service;

import java.util.List;
import java.util.Map;

import com.db.sys.pojo.SysDept;
import com.db.sys.vo.ZtreeNode;

public interface SysDeptService {

	List<Map<String, Object>> doFindObjects();

	int doDeleteObject(Long id);

	List<ZtreeNode> doFindZTreeNodes();

	void doSaveObject(SysDept dept);

	void doUpdateObject(SysDept dept);

	
}
