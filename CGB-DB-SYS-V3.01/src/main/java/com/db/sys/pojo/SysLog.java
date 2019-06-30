package com.db.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {

//id              bigint(20)
//username                   varchar(50)用户名
//operation                    varchar(50)用户操作
//method                   varchar(200)请求方法
//params                      varchar(5000)请求参数
//time                         bigint(20)执行时长(毫秒)
//ip                                            varchar(64)IP地址
//createdTime                 datetime创建时间
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8766157962777050231L;
	private Long Id;
	private  String username;
	private String operation;
	private String method;
	private String params;
	private Long time;
	private String ip;
	private Date createdTime;

}
