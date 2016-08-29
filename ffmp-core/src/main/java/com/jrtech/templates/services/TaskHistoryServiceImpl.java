package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.FlowchartSteps;
import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.repositories.HistoryTaskNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.
 */
@Service
public class TaskHistoryServiceImpl implements TaskHistoryService {
    @Autowired
    private HistoryTaskNodeRepository repository;
    @Override
    public HistoryTaskNode save(HistoryTaskNode historyTaskNode) {
        return repository.save(historyTaskNode);
    }

    @Override
    public HistoryTaskNode findOneByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public HistoryTaskNode findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<HistoryTaskNode> findByMaintenanceTaskOrderByDueDateDesc(MaintenanceTask maintenanceTask) {
        return repository.findByMaintenanceTaskOrderByDueDateDesc(maintenanceTask);
    }

    @Override
    public List<HistoryTaskNode> findByMaintenanceTaskAndFlowchartSteps(MaintenanceTask maintenanceTask, FlowchartSteps flowchartSteps) {
        return repository.findByMaintenanceTaskAndFlowchartSteps(maintenanceTask, flowchartSteps);
    }
}
