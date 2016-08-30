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
@JsonIgnoreProperties(value = {"maintenanceTask","delegate"})
public class HistoryTaskNode extends AbstractNamedObject {
    //任务
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
    private MaintenanceTask maintenanceTask;
    //任务节点执行者
    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH})
    private Account delegate;
    //是否中止任务
    private boolean suspended;
    //备注任务参数
    private String description;
    //任务步骤
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private FlowchartSteps flowchartSteps;
    //执行日期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date dueDate;

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

    public FlowchartSteps getFlowchartSteps() {
        return flowchartSteps;
    }

    public void setFlowchartSteps(FlowchartSteps flowchartSteps) {
        this.flowchartSteps = flowchartSteps;
    }
}
