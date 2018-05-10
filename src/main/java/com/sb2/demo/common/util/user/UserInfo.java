package com.sb2.demo.common.util.user;


import com.sb2.demo.common.util.node.NodeUtil;
import com.sb2.demo.sys.entity.Tree;
import com.sb2.demo.sys.entity.User;
import com.sb2.demo.sys.service.TreeService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class UserInfo {


    /**
     * 功能描述:加载用户的菜单和按钮数据
     * @param treeService
     * @return
     */
    public static List<Tree> loadUserBackTree(TreeService treeService){
        List<Tree> treeList = new ArrayList<Tree>();
        Map<Long,Tree> treeMap = new HashMap<Long,Tree>();
        User user = getUser();
        for(Tree tree:treeService.loadUserTreeAndButton(user)){
            if(tree.getId()>55){
                treeMap.put(tree.getId(),tree);
            }
        }
        for(long key:treeMap.keySet()){
            treeList.add(treeMap.get(key));
        }
        return treeList;
    }

    /**
     * 功能描述：加载菜单节点的数据
     * @return
     */
    public static List<Tree> loadUserTree(TreeService treeService){
        Map<Long,Tree> treeMap = new HashMap<Long,Tree>();
        User user = getUser();
        for(Tree tree:treeService.loadUserTree(user)){
            treeMap.put(tree.getId(),tree);
        }
        List<Tree> treeList = NodeUtil.getChildNodes(new ArrayList<Tree>(treeMap.values()),0l);
        return treeList;
    }

    /**
     * 功能描述：实现对密码进行bcrypt加密
     * @param password
     * @return
     */
    public static String encode(String password){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 功能描述：获取当前登陆的用户的信息
     * 注释：强转一个null对象不会产生报错
     * @return
     */
    public static User getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        return (User)Optional.ofNullable(securityContextImpl.getAuthentication().getPrincipal()).orElse(null);
    }

    /**
     * 功能描述：获取当前登陆的用户的权限集合
     * @return
     */
    public static List<GrantedAuthority> getGrantedAuthority(){
        return (List<GrantedAuthority>)Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).orElse(new ArrayList<>());
    }

    /**
     * 功能描述：判断当前的用户是否包含某一个权限
     * @param authority
     * 注释：
     *      allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     *      anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     *      noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     * @return
     */
    public static boolean hasAuthority(String authority){
        return getGrantedAuthority().stream().anyMatch(obj->obj.getAuthority().equalsIgnoreCase(authority));
    }

    public static void main(String [] args){
        System.out.println(UserInfo.encode("123456"));
    }

}
