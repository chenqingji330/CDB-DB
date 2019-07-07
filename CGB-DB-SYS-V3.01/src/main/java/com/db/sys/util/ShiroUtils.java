package com.db.sys.util;

import org.apache.shiro.SecurityUtils;

import com.db.sys.pojo.SysUser;

public abstract class ShiroUtils {
	
	public static SysUser getUser() {
		return (SysUser)
		SecurityUtils.getSubject().getPrincipal();
	}

}
