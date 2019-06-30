package com.db.sys.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SysMenu extends BasePojo {

//id    int(11)
//name              varchar(50)资源名称
//url                             varchar(200)资源URL
//type                   int(11)类型 1：菜单 2：按钮
//sort                   int(11)排序
//note                                     varchar(100)备注
//parentId                       int(11)父菜单ID，一级菜单为0
//permission                          varchar(500)授权(如：user:create)
//createdTime                       datetime创建时间
//modifiedTime                     datetime修改时间
//createdUser                         varchar(20)创建用户
//modifiedUservar                  char(20)修改用户
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5957101606645616340L;
	private Long id;
	private String name;
	private String url;
	private Long type;
	private Long sort;
	private String note;
	private Long parentId;
	private String permission;
	

}
