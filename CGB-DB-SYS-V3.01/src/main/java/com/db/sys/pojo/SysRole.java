package com.db.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BasePojo {

	
	

//	id                  bigint(20)
//	name               varchar(100)角色名称
//	note                varchar(500)备注
//	createdTime                   datetime创建时间
//	modifiedTime                    datetime修改时间
//	createdUser                  varchar(20)创建用户
//	modifiedUser             varchar(20)修改用户

/**
	 * 
	 */
	private static final long serialVersionUID = -4643750379175917305L;
private Long id;
private String name;
private String note;

}
