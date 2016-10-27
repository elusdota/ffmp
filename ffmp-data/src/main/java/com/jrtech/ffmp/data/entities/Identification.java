package com.jrtech.ffmp.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by jiangliang on 2016/10/27.
 */
@Entity
@Table(name = "Identification")
public class Identification extends AbstractDocumentObject {
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
