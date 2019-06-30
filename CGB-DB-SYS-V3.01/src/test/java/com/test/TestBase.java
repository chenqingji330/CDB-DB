package com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
	
	protected  ClassPathXmlApplicationContext ctx;
	
	//ClassPathXmlApplicationContext为基于xml方式的Context对象。
	
	@Before
	public void init() {
		 ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
		
	}
	@Test//测试加载配置文件
	public void testCtx() {
		
		System.out.println(ctx);
		
	}
	@After//关闭加载配置文件
	public void destory() {
		ctx.close();
		
	}

}
