package com.sb2.demo.sys.entity;


import com.sb2.demo.common.base.entity.QueryBase;

/**
 *@author linzf
 **/
public class QueryUserRole extends QueryBase {
	private String name;
	private String roleName;
	// 创建人ID
	private int createId;

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
