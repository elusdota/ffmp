package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.KeyPartStandard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 重点部位标准服务层
 * Created by suelmer on 2016/7/18.
 */
public interface KeyPartStandardService {

    /**
     * 保存/修改重点部位标准
     * @param keyPartStandard  实体
     * @return  KeyPartStandard
     */
    KeyPartStandard save(KeyPartStandard keyPartStandard);

    /**
     * 通过id查询一个重点部位标准
     * @param id  id
     * @return  KeyPartStandard
     */
    KeyPartStandard findOne(String id);

    /**
     * 通过id删除一个重点部位标准
     * @param id  id
     */
    void delete(String id);

    /**
     * 查询所有的重点标准
     * @param spec  查询条件组合
     * @param pageable  分页信息
     * @return  page对象
     */
    Page<KeyPartStandard> findAll(Specification<KeyPartStandard> spec, Pageable pageable);
}
