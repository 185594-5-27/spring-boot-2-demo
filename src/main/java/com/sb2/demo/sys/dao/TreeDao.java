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
     * 功能描述：根据父节点ID来删除数据
     * @param entity
     * @return
     */
    int deleteByPId(Tree entity);

    /**
     * 功能描述：
     * @param user
     * @return
     */
    List<Tree> loadUserTreeAndButton(User user);

    /**
     * 功能描述：加载用户的菜单树的数据
     * @param user
     * @return
     */
	List<Tree> loadUserTree(User user);

    /**
     * 功能描述：加载用户的按钮的数据
     * @param user
     * @return
     */
    List<Tree> loadUserButton(User user);

}