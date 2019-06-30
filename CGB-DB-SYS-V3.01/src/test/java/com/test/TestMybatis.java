package com.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class TestMybatis extends TestBase {

	
	@Test
	public void testMybatis() {
		SqlSessionFactory factory = ctx.getBean("sqlSessionFactory", SqlSessionFactory.class);
		System.out.println(factory);
		SqlSession session = factory.openSession();
		System.out.println(session);
		
	}
}
