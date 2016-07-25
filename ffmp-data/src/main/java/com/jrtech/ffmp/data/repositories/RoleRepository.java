package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
	 /**
     * 通过角色名称查询角色
     * @param name 角色名称
     * @return 角色对象
     */
    Role findOneByName(String name);
}
