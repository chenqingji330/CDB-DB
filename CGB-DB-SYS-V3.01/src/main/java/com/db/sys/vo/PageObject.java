package com.db.sys.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class PageObject<T> implements Serializable {
	
	/**当前页要呈现的记录*/
	 private List<T> records;
	 /**总记录数*/
	 private Integer rowCount;
	 /**总页数*/
	 private Integer pageCount;
	 /**页面大小*/
	 private Integer pageSize;
	 /**当前页页码*/
	 private Integer pageCurrent;

}
