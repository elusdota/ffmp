package com.jrtech.templates.services;


import com.jrtech.ffmp.data.entities.Organization;

public interface OrganizationService {

    /**
     * 获取根节点组织机构
     *
     * @return 组织机构对象
     */
    Organization findRoot();

    /**
     * 通过id查询组织机构对象
     *
     * @param id id
     * @return 组织机构对象
     */
    Organization findOne(String id);

    Organization findOneByName(String name);

    /**
     * 保存组织机构对象
     *
     * @param organization 组织机构对象
     * @return 组织机构对象
     */
    Organization save(Organization organization);

    /**
     * 通过id删除组织机构对象
     *
     * @param id
     */
    void delete(String id);

    /**
     * 组织机构对象是否已经存在
     *
     * @param org 组织机构
     * @return boolean true:已经存在，false:不重复
     */
    boolean isDuplicateNameOnSameLevel(Organization org);

}