package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Overflow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/8/15.报溢单service接口，elus
 */
public interface OverflowService {
    /**
     * 查出所有报溢单
     *
     * @return 报溢单列表
     */
    Page<Overflow> findAll(Specification<Overflow> spec, Pageable pageable);

    /**
     * 保存报溢单
     *
     * @param overflow
     * @return 报溢单
     */
    Overflow save(Overflow overflow);

    /**
     * 通过id查询报溢单
     *
     * @param id
     * @return
     */
    Overflow findOne(String id);

    /**
     * 通过id删除报溢单
     *
     * @param id
     */
    void delete(String id);
}
