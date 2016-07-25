package com.jrtech.templates.services;

import java.util.Collection;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

	 /**
     * 查询所有账户
     *
     * @return 账户列表
     */
    Page<Account> findAll(Pageable pageable);

    /**
     * 通过账户名称查询账户对象
     *
     * @param name 账户名称
     * @return 账户对象
     */
    Account findOneByName(String name);

    /**
     * 保存账户
     *
     * @param account 账户对象
     * @return 账户对象
     */
    Account save(Account account);

    /**
     * 通过id获取账户对象
     *
     * @param id 主键
     * @return 账户对象
     */
    Account findOne(String id);

    /**
     * 通过id删除账户
     *
     * @param id
     */
    void delete(String id);

    /**
     * 通过账户名称获取账户权限列表
     *
     * @param accountName 账户名称
     * @return 权限列表
     */
    Collection<GrantedAuthorityImpl> getAuthorities(String accountName);

    /**
     * 通过账户名称获取账户拥有人所属组织机构列表
     *
     * @param accountName 账户名称
     * @return 组织机构列表
     */
    Collection<Organization> getOrganizations(String accountName);
    Page<Account> findByNameContaining(String name, Pageable pageable);


}