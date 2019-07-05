package com.db.sys.vo;

import com.db.sys.pojo.BasePojo;
import com.db.sys.pojo.SysDept;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SysUserDeptVo extends BasePojo {

/**
	 * 
	 */
	private static final long serialVersionUID = 5248480517835521325L;
	//	id                             int(11)
//	username                 varchar(50)用户名
//	password                   varchar(100)密码
//	salt                               varchar(50)盐 密码加密时前缀，使加密后的值不同
//	email                                varchar(100)邮箱
//	mobile                                  varchar(100)手机号
//	validtiny                                  int(4)状态 0：禁用 1：正常 默认值 ：1
//	deptId                                               int(11)
//	createdTime                                datetime创建时间
//	modifiedTime                          datetime修改时间
//	createdUser                                varchar(20)创建用户
//	modifiedUser                    varchar(20)修改用户
	private Long id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Long valid;
	private SysDept sysDept;//private Integer deptId;

	
	
	
	
	
	
	
	

}
