package com.sb2.demo.sys.entity;


import java.util.Date;

/**
 *@author linzf
 **/
public class OrgGroup {
	private long groupId;
	private long existingNum;
	private String groupCode;
	private String name;
	private String node;
	private long num;
	private String parentNode;
	// 创建人ID
	private int createId;
	// 创建人名字
	private String createName;
	// 创建时间
	private Date createTime;
	// 父部门信息
	private OrgGroup orgGroup;

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public OrgGroup getOrgGroup() {
		return orgGroup;
	}

	public void setOrgGroup(OrgGroup orgGroup) {
		this.orgGroup = orgGroup;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getExistingNum() {
		return existingNum;
	}

	public void setExistingNum(long existingNum) {
		this.existingNum = existingNum;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

}
