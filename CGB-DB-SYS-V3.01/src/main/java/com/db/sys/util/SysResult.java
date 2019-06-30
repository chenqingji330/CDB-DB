package com.db.sys.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SysResult {
	private int state=1;
	/**状态码对应的状态信息*/
	private String message="ok";
	/**正确数据(输出到客户端的数据)*/
	private Object data;
	
	public SysResult() {
		
	}

	public SysResult(Object data) {
		super();
		this.data = data;
	}

	public SysResult(String message) {
		super();
		this.message = message;
	}
	
	
	public SysResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage();
		
	}

	public SysResult(int state, String message, Object data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	

	


}
