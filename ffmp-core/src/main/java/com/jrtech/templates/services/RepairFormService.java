package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.RepairForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/7/25.报修单服务接口，elus
 */
public interface RepairFormService {
    RepairForm save(RepairForm repairForm);

    RepairForm findOne(String id);

    Page<RepairForm> findAll(Specification<RepairForm> spec, Pageable pageable);

    RepairForm findOneByCode(String code);

    Page<RepairForm> findByProcessing(boolean processing, Pageable pageable);

}
