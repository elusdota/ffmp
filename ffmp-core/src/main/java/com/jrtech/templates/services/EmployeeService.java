package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/8/11.职员业务代码
 */
public interface EmployeeService {
    /**
     * 分页查询职员
     * @param spec 查询规格
     * @param pageable 分页参数
     * @return 分页职员对象
     */
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    /**
     * 保存
     * @param employee 职员对象
     * @return 职员对象
     */
    Employee save(Employee employee);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Employee findOne(String id);

    Employee findOneByCode(String code);
}
