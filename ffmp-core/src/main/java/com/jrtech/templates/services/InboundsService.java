package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inbounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/6/28.入库单Service,elus
 */
public interface InboundsService {
    /**
     * 查出所有入库单
     * @return 入库单列表
     */
    Page<Inbounds> findAll(Specification<Inbounds> spec, Pageable pageable);

    /**
     * 保存入库单
     * @param inbounds
     * @return 入库单
     */
    Inbounds save(Inbounds inbounds);

    /**
     * 通过id查询入库单
     * @param id
     * @return
     */
    Inbounds findOne(String id);

    /**
     * 通过id删除入库单
     * @param id
     */
    void delete(String id);
}
