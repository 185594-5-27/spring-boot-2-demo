package com.sb2.demo.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@author linzf
 **/
public class UserRole {
	private long id;
	private String name;
	private String roleName;
	// 创建人ID
	private int createId;
	// 创建人名字
	private String createName;
	// 创建时间
	private Date createTime;

	private List<Tree> treeList;
    // 临时采访菜单数集合的数据
	private String treeArray;

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

	public String getTreeArray() {
		return treeArray;
	}

	public void setTreeArray(String treeArray) {
		this.treeArray = treeArray;
	}

	public List<Tree> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree> treeList) {
		this.treeList = treeList;
	}

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void packagingTrees(String treeArray){
		Tree tree = null;
		List<Tree> trees = new ArrayList<>();
		for(String id:treeArray.split(",")){
			if(!id.isEmpty()){
				tree = new Tree(Long.parseLong(id));
				trees.add(tree);
			}
		}
		this.setTreeList(trees);
	}

}
