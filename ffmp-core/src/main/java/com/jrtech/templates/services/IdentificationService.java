package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.ffmp.data.entities.Identification;

/**
 * Created by jiangliang on 2016/10/27.证件业务处理，elus
 */
public interface IdentificationService {
    Identification findOne(String id);

    Identification save(Identification identification);

    void delete(String id);

    Iterable<Identification> findByEmployee(Employee employee);
}
