package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Shawn on 2015/12/4.
 */
@Repository
public interface GrantedAuthorityRepository extends CrudRepository<GrantedAuthorityImpl, String> {
    /**
     * 查询基础菜单
     *
     * @return 权限列表
     */
    @Query("SELECT p FROM GrantedAuthorityImpl p WHERE p.parent is null")
    List<GrantedAuthorityImpl> findRoot();

    /**
     * 权限名称查询权限列表
     *
     * @param name
     * @return 权限列表
     */
    @Query("SELECT p FROM GrantedAuthorityImpl p WHERE p.name like :keyword")
    List<GrantedAuthorityImpl> searchByName(@Param("keyword") String name);

    GrantedAuthorityImpl findOneByName(String name);
}
