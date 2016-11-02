package com.jrtech.ffmp.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 任务，elus
 *
 * @author jiangliang
 */
@Entity
@Table(name = "MaintenanceTask")
@JsonIgnoreProperties(value = {"delegate","taskDefinition","owner"})
public class MaintenanceTask extends AbstractNamedObject {
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private TaskDefinition taskDefinition;
    //任务创建者，所有者
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private Account owner;
    //所属项目
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private MaintenanceProject maintenanceProject;
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    //客户
    private Customer customer;
    //是否中止任务
    private boolean suspended;
    //报修单编号
    private String repairnumber;
    //委托执行机构
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private Organization delegate;
    //起始日期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date startdate;
    //结束日期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date enddate;
    //备注
    private String description;
    //类型
    private String type;

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public MaintenanceProject getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(MaintenanceProject maintenanceProject) {
        this.maintenanceProject = maintenanceProject;
    }

    public String getRepairnumber() {
        return repairnumber;
    }

    public void setRepairnumber(String repairnumber) {
        this.repairnumber = repairnumber;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public Organization getDelegate() {
        return delegate;
    }

    public void setDelegate(Organization delegate) {
        this.delegate = delegate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
