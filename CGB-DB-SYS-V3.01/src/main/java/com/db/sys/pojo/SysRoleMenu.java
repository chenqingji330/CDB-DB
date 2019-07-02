package com.db.sys.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class SysRoleMenu  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2511370651284484311L;
	private Long id;
	private Long role_id;
	private Long menu_id;
	

}
