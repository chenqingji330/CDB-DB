package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.db.sys.pojo.SysDept;
import com.db.sys.vo.ZtreeNode;

public interface SysDeptDao {

	List<Map<String, Object>> doFindObjects();

	int doDeleteObject(Long id);

	List<ZtreeNode> doFindZTreeNodes();

	void doSaveObject(SysDept dept);

	int doUpdateObject(SysDept dept);

}
