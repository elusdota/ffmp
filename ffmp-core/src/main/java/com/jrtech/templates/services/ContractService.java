package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Contract;
import com.jrtech.templates.vo.ContractVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 合同信息接口
 * Created by suelmer on 2016/7/18.
 */
public interface ContractService {

    /**
     * 创建合同信息
     * @param contractVO 合同信息
     * @return 合同
     */
    Contract save(ContractVO contractVO);

    /**
     * 根据id 查找合同
     * @param id id
     * @return 合同
     */
    Contract findOne(String id);

    /**
     * 根据id 删除合同
     * @param id id
     * @return 合同
     */
    void delete(String id);

    /**
     * 更新合同信息
     * @param contractVO 合同信息
     * @return
     */
   Contract update(ContractVO contractVO);
    /**
     * 查询所有合同
     * @param spec  查询参数
     * @param pageable  分页参数
     * @return
     */
    Page<Contract> findAll(Specification<Contract> spec, Pageable pageable);
}
