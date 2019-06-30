package com.db.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class SysDept extends BasePojo {

//	id                       int(11)
//	name                                varchar(50)资源名称
//	parentId       int(11)上级部门
//	sort                 int(11)排序
//	note                      varchar(100)备注
//	createdTime                    datetime创建时间
//	modifiedTime                       datetime修改时间
//	createdUser                     varchar(20)创建用户
//	modifiedUser                   varchar(20)修改用户

/**
	 * 
	 */
	private static final long serialVersionUID = 9039328001993943365L;
private Long  id;
private String name;
private Long parentId;
private Long sort;
private String note;


}
