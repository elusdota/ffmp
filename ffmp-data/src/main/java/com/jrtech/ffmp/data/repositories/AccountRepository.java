package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, String>, JpaSpecificationExecutor {
	 /**
     * 通过名称查询账户
     *
     * @param name 账户名称
     * @return 账户
     */
	Account findOneByName(String name);
	/**
	 * 通过名称模糊查询账户分页
	 * @param name 名称
	 * @param pageable 分页对象
	 * @return 列表
	 */
	Page<Account> findByNameContaining(String name, Pageable pageable);
}
