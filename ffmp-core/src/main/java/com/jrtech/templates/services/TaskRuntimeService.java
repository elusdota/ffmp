package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/7/14.
 */
public interface TaskRuntimeService {
    //查询
    Page<MaintenanceTask> findAll(Specification<MaintenanceTask> spec, Pageable pageable);

    //保存
    MaintenanceTask save(MaintenanceTask maintenanceTask);

    MaintenanceTask findOneByRepairnumber(String repairnumber);

    //id查询
    MaintenanceTask findOne(String id);

    //获取登录用户分页任务列表
    Page<MaintenanceTask> findBySuspended(Pageable pageable);
}
