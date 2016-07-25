package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;

import java.util.List;


public interface GrantedAuthorityService {

    /**
     * 获取权限根节点列表
     *
     * @return 权限列表
     */
    List<GrantedAuthorityImpl> findRoot();

    /**
     * 通过id获取权限对象
     *
     * @param id id
     * @return 权限对象
     */
    GrantedAuthorityImpl findOne(String id);

    GrantedAuthorityImpl findOneByName(String name);

    /**
     * 保存权限对象
     *
     * @param permission 权限对象
     * @return 权限对象
     */
    GrantedAuthorityImpl save(GrantedAuthorityImpl permission);

    /**
     * 通过id删除权限对象
     *
     * @param id id
     */
    void delete(String id);

    /**
     * 通过关键字查询权限列表
     *
     * @param keyword 关键字
     * @return 权限列表
     */
    List<GrantedAuthorityImpl> searchByName(String keyword);

}