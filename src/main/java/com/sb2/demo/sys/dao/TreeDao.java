package com.sb2.demo.sys.dao;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.sys.entity.QueryTree;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.entity.User;

import java.util.List;

/**
 *@author linzf
 **/
public interface TreeDao extends GenericDao<Tree, QueryTree> {

    /**
     * 功能描述：加载用户的菜单树的数据
     * @param user
     * @return
     */
	List<Tree> loadUserTree(User user);
}