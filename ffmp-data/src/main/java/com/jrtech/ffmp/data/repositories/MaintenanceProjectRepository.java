package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.项目数据接口
 */
@Repository
public interface MaintenanceProjectRepository extends PagingAndSortingRepository<MaintenanceProject, String>, JpaSpecificationExecutor {
    MaintenanceProject findOneByName(String name);

    List<MaintenanceProject> findByNameLike(String name);

    MaintenanceProject findOneByCode(String code);

    Iterable<MaintenanceProject> findByTerminate(boolean terminate);
}
