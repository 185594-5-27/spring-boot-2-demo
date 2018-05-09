package com.sb2.demo.sys.service;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.common.base.entity.Page;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.common.util.user.UserInfo;
import com.sb2.demo.sys.dao.OrgGroupDao;
import com.sb2.demo.sys.dao.UserDao;
import com.sb2.demo.sys.entity.OrgGroup;
import com.sb2.demo.sys.entity.QueryOrgGroup;
import com.sb2.demo.sys.entity.QueryUser;
import com.sb2.demo.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *@author linzf
 **/
@Service("orgGroupService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class OrgGroupService extends GenericService<OrgGroup, QueryOrgGroup> {

	@Autowired
	private OrgGroupDao orgGroupDao;

	@Autowired
	private UserDao userDao;


	@Override
	protected GenericDao<OrgGroup, QueryOrgGroup> getDao() {
		return orgGroupDao;
	}

	@Override
	public boolean delete(OrgGroup entity) throws Exception {
		QueryUser queryUser = new QueryUser();
		queryUser.setOrgGroup(entity);
		if(userDao.countGroupUser(queryUser)>0){
			return false;
		}
		return super.delete(entity);
	}

	@Override
	public List<OrgGroup> query(QueryOrgGroup queryModel) {
		if(!UserInfo.hasAuthority("ROLE_ADMIN")){
			if(queryModel==null){
				queryModel = new QueryOrgGroup();
			}
			queryModel.setCreateId(UserInfo.getUser().getId());
		}
		return super.query(queryModel);
	}

	@Override
	public Page findByPage(QueryOrgGroup queryModel) {
		// 查询组织架构的时候判断当前查询的用户是否有系统管理员权限，若是有则可以查询全部的组织架构
		if(!UserInfo.hasAuthority("ROLE_ADMIN")){
			queryModel.setCreateId(UserInfo.getUser().getId());
		}
		return super.findByPage(queryModel);
	}

	@Override
	public boolean save(OrgGroup entity) throws Exception {
		User user = UserInfo.getUser();
		entity.setCreateId(user.getId());
		entity.setCreateName(user.getUserName());
		entity.setCreateTime(new Date());
		return super.save(entity);
	}

	/**
	 * 功能描述：根据父节点来查询最大的节点的值
	 * @param parentNode
	 * @return
	 */
	public String getMaxOrgGroup(String parentNode){
		return orgGroupDao.getMaxOrgGroup(parentNode);
	}

	/**
	 * 功能描述：根据菜单节点NODE来查询节点数据
	 * @param node
	 * @return
	 */
	public OrgGroup findByNode(String node){
		return orgGroupDao.findByNode(node);
	}
}