package com.jrtech.templates.services;


import com.jrtech.ffmp.data.entities.Role;

public interface RoleService {
    /**
     * 通过id查询角色对象
     *
     * @param id id
     * @return 角色对象
     */
    Role findOne(String id);

    /**
     * 保存角色对象
     *
     * @param role 角色对象
     * @return 角色对象
     */
    Role save(Role role);

    /**
     * 通过id删除角色对象
     *
     * @param id id
     */
    void delete(String id);

    Role findOneByName(String name);

    /**
     * 判断角色对象是否已经存在
     *
     * @param role 角色对象
     * @return true:已经存在，false:不重复
     */
    boolean isDuplicateNameOnSameLevel(Role role);

}