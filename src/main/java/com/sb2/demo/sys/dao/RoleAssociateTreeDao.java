package com.sb2.demo.sys.dao;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.sys.entity.QueryRoleAssociateTree;
import com.sb2.demo.sys.entity.RoleAssociateTree;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.entity.UserRole;

/**
 *@author linzf
 **/
public interface RoleAssociateTreeDao extends GenericDao<RoleAssociateTree, QueryRoleAssociateTree> {

    /**
     * 功能描述：根据菜单ID来删除关联的菜单数据
     * @param tree
     * @return
     */
    int removeTreeByTreeId(Tree tree);

    /**
     * 功能描述：根据角色ID来删除关联的菜单数据
     * @param userRole
     * @return
     */
    int removeTreeByRoleId(UserRole userRole);
	
}