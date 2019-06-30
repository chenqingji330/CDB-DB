package com.test;

import org.junit.Test;

import com.db.sys.dao.SysLogDao;

public class TestMapper  extends TestBase {
	
	@Test
	public void TestMapper() {
		SysLogDao dao = ctx.getBean("sysLogDao", SysLogDao.class);
		int rows = dao.deleteByIds(245,246,247,248,249,250,254,255,265);
		System.out.println(rows);
	}
	
	
	
	

}
