package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.*;

import java.util.Date;

/**
 * Created by suelmer on 2016/10/17.
 */
public class MaintenanceTaskVO {

    private String id ;
    private String name ;
    private TaskDefinition taskDefinition;
    //任务创建者，所有者
    private Account owner;
    //所属项目
    private MaintenanceProject maintenanceProject;
    //客户
    private Customer customer;
    //是否中止任务
    private boolean suspended;
    //报修单编号
    private String repairnumber;
    //委托执行机构
    private Organization delegate;
    //起始日期
    private Date startdate;
    //结束日期
    private Date enddate;
    //备注
    private String description;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Organization getDelegate() {
        return delegate;
    }

    public void setDelegate(Organization delegate) {
        this.delegate = delegate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MaintenanceProject getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(MaintenanceProject maintenanceProject) {
        this.maintenanceProject = maintenanceProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public String getRepairnumber() {
        return repairnumber;
    }

    public void setRepairnumber(String repairnumber) {
        this.repairnumber = repairnumber;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}
