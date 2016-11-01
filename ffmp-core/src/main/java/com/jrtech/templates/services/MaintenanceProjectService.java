package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.项目服务接口，elus
 */
public interface MaintenanceProjectService {
    Page<MaintenanceProject> findAll(Specification<MaintenanceProject> spec, Pageable pageable);

    Iterable<MaintenanceProject> findAll();

    Iterable<MaintenanceProject> findByTerminate(boolean terminate);

    MaintenanceProject findOneByName(String name);

    MaintenanceProject findOneByCode(String code);

    MaintenanceProject findOne(String id);

    MaintenanceProject save(MaintenanceProject maintenanceProject);

    /**
     * 通过名称模糊查询
     *
     * @param name
     * @return
     */
    List<MaintenanceProject> findByNameLike(String name);
}
