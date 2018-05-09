package com.sb2.demo.sys.controller;


import com.sb2.demo.common.base.constant.SystemStaticConst;
import com.sb2.demo.common.base.controller.GenericController;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.common.util.json.GsonHelper;
import com.sb2.demo.common.util.json.JsonHelper;
import com.sb2.demo.sys.entity.QueryUserRole;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.entity.UserRole;
import com.sb2.demo.sys.mapper.TreeMapper;
import com.sb2.demo.sys.service.TreeService;
import com.sb2.demo.sys.service.UserRoleService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：
* @auther linzf
* @create 2017/10/10 0010 
*/
@Controller
@RequestMapping("/role")
public class RoleController extends GenericController<UserRole,QueryUserRole> {

    @Inject
    private UserRoleService userRoleService;

    @Inject
    private TreeService treeService;

    @Inject
    private TreeMapper treeMapper;

    @Override
    protected GenericService<UserRole, QueryUserRole> getService() {
        return userRoleService;
    }

    @Override
    public Map<String, Object> removeBath(String json) throws Exception {
        Map<String,Object> result = new HashMap<String, Object>();
        if(userRoleService.removeBath((List<UserRole>)JsonHelper.toList(json,UserRole.class))){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"删除数据成功！");
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"删除数据失败！请先删除该角色与之关联的用户，再来删除相应的角色！");
        }
        return result;
    }

    /**
     * 功能描述：根据用户的权限去加载角色数据
     * @return
     */
    @RequestMapping(value = "/loadRoleTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadRoleTree(UserRole entity){
        entity = userRoleService.getUserRoleAssociate(entity);
        List<Tree> treeList = treeService.query(null);
        if(entity!=null){
            for(Tree tree:entity.getTreeList()){
                treeList.stream().forEach(t->{
                    if(t.getId()==tree.getId()){
                        t.setChecked(true);
                    }
                });
            }
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data",treeMapper.treesToTressDTOs(treeList));
        return result;
    }

}
