package com.sb2.demo.sys.controller;


import com.sb2.demo.common.base.constant.SystemStaticConst;
import com.sb2.demo.common.base.controller.GenericController;
import com.sb2.demo.common.base.entity.Page;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.common.util.user.UserInfo;
import com.sb2.demo.sys.entity.QueryTree;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.mapper.TreeMapper;
import com.sb2.demo.sys.service.TreeService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：菜单操作controller
* @auther linzf
* @create 2017/10/10 0010 
*/
@Controller
@RequestMapping("/tree")
public class TreeController extends GenericController<Tree,QueryTree> {

    @Inject
    private TreeService treeService;
    @Inject
    private TreeMapper treeMapper;

    @Override
    protected GenericService<Tree, QueryTree> getService() {
        return treeService;
    }

    /**
     * 功能描述：跳转到修改菜单按钮节点的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateTreeButtonPage")
    public String updateTreeButtonPage(Tree entity,Model model) throws Exception{
        entity = treeService.get(entity);
        Tree pTree = treeService.get(new Tree(entity.getpId()));
        entity.setTree(pTree);
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+"/updateButton";
    }

    /**
     * 功能描述：跳转到增加菜单按钮节点的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addTreeButtonPage")
    public String addTreeButtonPage(Tree entity,Model model) throws Exception{
        entity = treeService.get(entity);
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+"/addButton";
    }

    /**
     * 功能描述：获取菜单底下的按钮节点数据
     * @param queryTree
     * @return
     */
    @RequestMapping(value="/treeButtonList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> treeButtonList(QueryTree queryTree){
        Map<String,Object> result = new HashMap<String, Object>();
        Page page = treeService.findByPage(queryTree);
        result.put("totalCount",page.getTotal());
        result.put("result",page.getRows());
        return result;
    }

    /**
     * 功能描述：跳转到修改菜单节点的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateTreePage")
    public String updateTreePage(Tree entity,Model model) throws Exception{
        entity = treeService.get(entity);
        Tree pTree = null;
        if(entity.getpId()==0l){
            pTree = new Tree();
            pTree.setId(0l);
            pTree.setName("顶层节点");
        }else{
            pTree = treeService.get(new Tree(entity.getpId()));
        }
        entity.setTree(pTree);
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+UPDATEPAGE;
    }

    /**
     * 功能描述：跳转到增加菜单节点的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addTreePage")
    public String addPage(Tree entity,Model model) throws Exception{
        if(entity.getId()==0){
            entity = new Tree();
            entity.setId(0l);
            entity.setName("顶层节点");
        }else{
            entity = treeService.get(entity);
        }
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+ADDPAGE;
    }

    /**
     * 功能描述：直接加载整个菜单树的数据
     * @return
     */
    @RequestMapping(value = "/loadUserTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadUserTree(QueryTree queryTree){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Tree> treeList = treeService.query(queryTree);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"加载菜单数据成功！");
        result.put("data",treeMapper.treesToTressDTOs(treeList));
        return result;
    }

    /**
     * 功能描述：根据当前登陆的用户来加载相应的按钮数据
     * @return
     */
    @RequestMapping(value = "/loadTreeButton",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadTreeButton(QueryTree queryTree){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Tree> treeList = treeService.loadUserButton(UserInfo.getUser());
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"加载菜单数据成功！");
        result.put("data",treeMapper.treesToTressDTOs(treeList));
        return result;
    }


}
