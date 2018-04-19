package com.sb2.demo.sys.dao;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.sys.entity.QueryUserRole;
import com.sb2.demo.sys.entity.UserRole;

/**
 *@author linzf
 **/
public interface UserRoleDao extends GenericDao<UserRole, QueryUserRole> {

    /**
     * 功能描述：获取权限菜单数据
     * @param entity
     * @return
     */
    UserRole getUserRoleAssociate(UserRole entity);
	
}