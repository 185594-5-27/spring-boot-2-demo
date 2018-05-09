package com.sb2.demo.sys.entity;


import com.sb2.demo.common.base.entity.QueryBase;

/**
 *@author linzf
 **/
public class QueryTree extends QueryBase {
	private String code;
	private String icon;
	private String name;
	private Long pId;
	private Long treeOrder;
	private String url;
	private String state;
	// 当前菜单节点类型（1：菜单节点；2：按钮节点）
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPId() {
		return pId;
	}

	public void setPId(Long pId) {
		this.pId = pId;
	}

	public Long getTreeOrder() {
		return treeOrder;
	}

	public void setTreeOrder(Long treeOrder) {
		this.treeOrder = treeOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
