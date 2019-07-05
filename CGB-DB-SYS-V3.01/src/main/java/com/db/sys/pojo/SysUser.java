package com.db.sys.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SysUser extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1541223411594757365L;
	private Long id;
	private String username;
	private String password;
	private String salt;//盐值
	private String email;
	private String mobile;
	private Long valid=1l;
    private Long deptId;

}
