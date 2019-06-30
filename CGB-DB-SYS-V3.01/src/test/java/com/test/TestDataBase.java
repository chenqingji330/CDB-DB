package com.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class TestDataBase extends TestBase {
	
	@Test
	public void testDataSource() throws Exception {
		DataSource ds = ctx.getBean("dataSource", DataSource.class);
		Connection coon = ds.getConnection();
		System.out.println(coon);
		
	}
	
	
	

}
