package com.db.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4067677384840832733L;
//createdTime                       datetime创建时间
	//modifiedTime                     datetime修改时间
	//createdUser                         varchar(20)创建用户
	//modifiedUser     varchar(20)修改用户
private Date  createdTime;
private Date  modifiedTime;
private String  createdUser;
private String  modifiedUser;
}
