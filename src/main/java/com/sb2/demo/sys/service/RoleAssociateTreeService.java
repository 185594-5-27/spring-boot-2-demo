package com.sb2.demo.sys.service;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.sys.dao.RoleAssociateTreeDao;
import com.sb2.demo.sys.entity.QueryRoleAssociateTree;
import com.sb2.demo.sys.entity.RoleAssociateTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("roleAssociateTreeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class RoleAssociateTreeService extends GenericService<RoleAssociateTree, QueryRoleAssociateTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;
	@Override
	protected GenericDao<RoleAssociateTree, QueryRoleAssociateTree> getDao() {
		return roleAssociateTreeDao;
	}
}