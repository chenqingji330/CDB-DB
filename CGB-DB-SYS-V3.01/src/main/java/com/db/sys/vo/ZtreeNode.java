package com.db.sys.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 这个类是用于封装树形节点的数据的
 * @author UID
 *
 */
@Data
@Accessors(chain=true)
public class ZtreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5553736632118083991L;
	private Integer id;
	private String name;
	private Integer parentId;

}
