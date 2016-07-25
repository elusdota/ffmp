package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceTask;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.
 */
public interface TaskHistoryService {
    //保存
    HistoryTaskNode save(HistoryTaskNode historyTaskNode);

    //名称查询
    HistoryTaskNode findOneByName(String name);

    //id查询
    HistoryTaskNode findOne(String id);

    List<HistoryTaskNode> findByMaintenanceTask(MaintenanceTask maintenanceTask);
}
