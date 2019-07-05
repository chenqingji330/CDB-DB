package com.db.sys.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class SysUserRole  implements Serializable{
	

//id                        int(11)
//user_id                      int(11)用户ID
//role_id                 int(11)角色ID
private Long id;
private Long user_id;
private Long role_id;

}
