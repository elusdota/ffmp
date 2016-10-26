package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/23.客户业务层，elus
 */
public interface CustomerService {
    /**
     * 分页查询
     * @param spec 查询参数
     * @param pageable 分页参数
     * @return 分页客户对象
     */
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

    /**
     * 查询所有客户
     * @return
     */
    Iterable<Customer> findAll();

    /**
     * 通过名称查询
     * @param name 名称
     * @return 客户对象
     */
    Customer findOneByName(String name);

    /**
     * 通过账户查询
     * @param account 账户
     * @return 客户对象
     */
    Customer findOneByAccount(Account account);

    /**
     * 保存
     * @param customer 客户对象
     * @return 客户对象
     */
    Customer save(Customer customer);

    /**
     * 删除
     * @param id id
     */
    void delete(String id);

    /**
     * 通过id查询
     * @param id id
     * @return 客户对象
     */
    Customer findOne(String id);

    /**
     * 判断客户是否已经存在
     * @param customer 客户对象
     * @return boolean
     */
    boolean isDuplicateNameOnSameLevel(Customer customer);

    /**
     * 通过名称模糊查询
     * @param name
     * @return
     */
    List<Customer> findByNameLike(String name);
}
