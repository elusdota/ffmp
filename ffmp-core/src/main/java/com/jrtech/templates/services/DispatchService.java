package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Dispatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/6/29.出库单SERVICE,elus
 */
public interface DispatchService {
    /**
     * 查出所有出库单
     */
    Page<Dispatch> findAll(Specification<Dispatch> spec, Pageable pageable);

    /**
     * 保存出库单
     * @param dispatch
     * @return 出库单
     */
    Dispatch save(Dispatch dispatch);

    /**
     * 删除出库单
     * @param id
     */
    void delete(String id);

    /**
     * 通过id查出出库单
     * @param id
     * @return
     */
    Dispatch findOne(String id);
}
