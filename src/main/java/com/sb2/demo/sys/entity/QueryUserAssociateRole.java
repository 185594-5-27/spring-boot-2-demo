package com.sb2.demo.sys.entity;


import com.sb2.demo.common.base.entity.QueryBase;

/**
 *@author linzf
 **/
public class QueryUserAssociateRole extends QueryBase {
	private Integer userId;
	private Long roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
