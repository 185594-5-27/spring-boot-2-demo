package com.sb2.demo.sys.service;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.common.util.user.UserInfo;
import com.sb2.demo.sys.dao.RoleAssociateTreeDao;
import com.sb2.demo.sys.dao.TreeDao;
import com.sb2.demo.sys.entity.QueryTree;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@author linzf
 **/
@Service("treeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class TreeService extends GenericService<Tree, QueryTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private TreeDao treeDao;
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;

	@Override
	protected GenericDao<Tree, QueryTree> getDao() {
		return treeDao;
	}

	@Override
	public List<Tree> query(QueryTree queryModel) {
		if(!UserInfo.hasAuthority("ROLE_ADMIN")){
			if(queryModel==null){
				queryModel = new QueryTree();
			}
			return UserInfo.loadUserBackTree(this);
		}
		return super.query(queryModel);
	}

	/**
	 * 功能描述：删除菜单的数据
	 * @param entity 删除对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean delete(Tree entity) throws Exception {
		Tree old = treeDao.get(entity);
		old.setpId(old.getId());
		// 删除按钮节点数据
		roleAssociateTreeDao.removeTreeByTreeId(old);
		treeDao.deleteByPId(old);
		// 删除当前菜单节点的数据
		roleAssociateTreeDao.removeTreeByTreeId(entity);
		return super.delete(entity);
	}

	/**
	 * 功能描述：加载菜单和按钮数据
	 * @param user
	 * @return
	 */
	public List<Tree> loadUserTreeAndButton(User user){
		return treeDao.loadUserTreeAndButton(user);
	}

	/**
	 * 功能描述：加载用户的菜单树的数据
	 * @param user
	 * @return
	 */
	public List<Tree> loadUserTree(User user){
		return treeDao.loadUserTree(user);
	}

	/**
	 * 功能描述：加载用户的按钮的数据
	 * @param user
	 * @return
			 */
	public List<Tree> loadUserButton(User user){
		return treeDao.loadUserButton(user);
	}


}