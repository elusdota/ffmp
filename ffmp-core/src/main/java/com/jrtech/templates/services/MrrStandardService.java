package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MrrStandard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by suelmer on 2016/7/16.
 */
public interface MrrStandardService {

    /**
     * 创建维管设施标准
     *
     * @param mrrStandard
     * @return MrrStandard
     */
    MrrStandard save(MrrStandard mrrStandard);

    /**
     * 通过id查询 维管设施标准
     *
     * @param id
     * @return
     */
    MrrStandard findOne(String id);

    Page<MrrStandard> findAll(Specification<MrrStandard> spec, Pageable pageable);

    Iterable<MrrStandard> findAll();

    Iterable<MrrStandard> findRoot();

    MrrStandard findOneByName(String name);

    MrrStandard findOneByParent(MrrStandard parent);
}
