package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {
    /**
     * 查询组织机构根对象
     *
     * @return 组织机构对象
     */
    @Query("SELECT o FROM Organization o WHERE o.parent is null")
    Organization findRoot();

    Organization findOneByName(String name);

    List<Organization> findByTypeAndNameLike(int type,String name);
}
