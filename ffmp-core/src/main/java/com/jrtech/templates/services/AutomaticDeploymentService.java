package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;

/**
 * Created by jiangliang on 2016/8/10.系统自动生成任务，elus
 */
public interface AutomaticDeploymentService {
    //生成巡检任务
    void buildScheduledTask();

    //生成替换设备任务
    void replacementEquipmentTask();
}
