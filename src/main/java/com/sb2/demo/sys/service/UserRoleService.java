package com.sb2.demo.sys.service;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.common.base.entity.Page;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.common.util.user.UserInfo;
import com.sb2.demo.sys.dao.RoleAssociateTreeDao;
import com.sb2.demo.sys.dao.UserAssociateRoleDao;
import com.sb2.demo.sys.dao.UserRoleDao;
import com.sb2.demo.sys.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *@author linzf
 **/
@Service("userRoleService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserRoleService extends GenericService<UserRole, QueryUserRole> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserRoleDao userRoleDao;
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;

	@Autowired
	private UserAssociateRoleDao userAssociateRoleDao;

	@Override
	protected GenericDao<UserRole, QueryUserRole> getDao() {
		return userRoleDao;
	}

	@Override
	public boolean delete(UserRole entity) throws Exception {
		QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
		queryUserAssociateRole.setRoleId(entity.getId());
		if(userAssociateRoleDao.query(queryUserAssociateRole).size()>0){
			return false;
		}
		return super.delete(entity);
	}

	@Override
	public Page findByPage(QueryUserRole queryModel) {
		// 查询组织架构的时候判断当前查询的用户是否有系统管理员权限，若是有则可以查询全部的组织架构
		if(!UserInfo.hasAuthority("ROLE_ADMIN")){
			queryModel.setCreateId(UserInfo.getUser().getId());
		}
		return super.findByPage(queryModel);
	}

	@Override
	public List<UserRole> query(QueryUserRole queryModel) {
		if(!UserInfo.hasAuthority("ROLE_ADMIN")){
			if(queryModel==null){
				queryModel = new QueryUserRole();
			}
			queryModel.setCreateId(UserInfo.getUser().getId());
		}
		return super.query(queryModel);
	}

	/**
	 * 功能描述：获取权限菜单数据
	 * @param entity
	 * @return
	 */
	public UserRole getUserRoleAssociate(UserRole entity){
		return userRoleDao.getUserRoleAssociate(entity);
	}

	/**
	 * 功能描述：删除角色数据
	 * @param entityList
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean removeBath(List<UserRole> entityList) throws Exception {
        for(UserRole userRole:entityList){
			roleAssociateTreeDao.removeTreeByRoleId(userRole);
		}
		return super.removeBath(entityList);
	}

	/**
	 * 增加角色数据
	 * @param entity 保存对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean save(UserRole entity) throws Exception {
		entity.packagingTrees(entity.getTreeArray());
		List<Tree> treeList = entity.getTreeList();
		User user = UserInfo.getUser();
		entity.setCreateId(user.getId());
		entity.setCreateName(user.getUserName());
		entity.setCreateTime(new Date());
		boolean success = super.save(entity);
		for(Tree tree:treeList){
			roleAssociateTreeDao.save(new RoleAssociateTree(entity.getId(),tree.getId()));
		}
		return success;
	}

	@Override
	public boolean update(UserRole entity) throws Exception {
		entity.packagingTrees(entity.getTreeArray());
		List<Tree> treeList = entity.getTreeList();
		roleAssociateTreeDao.removeTreeByRoleId(entity);
		for(Tree tree:treeList){
			roleAssociateTreeDao.save(new RoleAssociateTree(entity.getId(),tree.getId()));
		}
		return super.update(entity);
	}
}