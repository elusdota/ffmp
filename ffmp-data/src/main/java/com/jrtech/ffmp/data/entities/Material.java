package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by jiangliang on 2016/8/2.elus ,材料申请
 */
@Entity
@Table(name = "Material")
@JsonIgnoreProperties(value = {"maintenanceTask"})
public class Material extends AbstractDomainObject{
    //任务
    @NotNull
    @ManyToOne
    private MaintenanceTask maintenanceTask;
    //申请人
    @ManyToOne
    private Account delegate;

    public MaintenanceTask getMaintenanceTask() {
        return maintenanceTask;
    }

    public void setMaintenanceTask(MaintenanceTask maintenanceTask) {
        this.maintenanceTask = maintenanceTask;
    }

    public Account getDelegate() {
        return delegate;
    }

    public void setDelegate(Account delegate) {
        this.delegate = delegate;
    }
}
