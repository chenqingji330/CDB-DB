package com.db.sys.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class CheckBox implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5493516787777962387L;
	private Integer id;
	private String name;
	 
	

}
