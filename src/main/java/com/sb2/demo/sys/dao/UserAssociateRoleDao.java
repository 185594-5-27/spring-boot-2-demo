package com.sb2.demo.sys.dao;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.sys.entity.QueryUserAssociateRole;
import com.sb2.demo.sys.entity.User;
import com.sb2.demo.sys.entity.UserAssociateRole;

/**
 *@author linzf
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}