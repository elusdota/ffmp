package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by jiangliang on 2016/7/14.任务执行历史节点
 */
@Entity
@Table(name = "HistoryTaskNode")
@JsonIgnoreProperties(value = {"maintenanceTask"})
public class HistoryTaskNode extends AbstractNamedObject {
    //任务
    @NotNull
    @ManyToOne
    private MaintenanceTask maintenanceTask;
    //任务节点执行者
    @ManyToOne
    private Account delegate;
    //是否中止任务
    private boolean suspended;
    //备注
    private String description;
    //执行类型 operation：操作，strat:开始，end:结束，subroutine：子节点，condition,条件，inputoutput：输入输出
    private String type;
    //执行日期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date dueDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Equipment> equipments=new ArrayList<Equipment>();

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
