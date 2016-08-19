package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.TaskEquipemtService;
import com.jrtech.templates.services.TaskRuntimeService;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/8/18.
 */
@RestController
@RequestMapping(value = "/rest/taskEquipemt")
public class TaskEquipemtController {
    @Autowired
    private TaskEquipemtService service;
    @Autowired
    private TaskRuntimeService taskRuntimeService;

    @RequestMapping(method = RequestMethod.GET)
    public JSONListData get(@RequestParam("id") String id) {
        JSONListData jld = new JSONListData();
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(id);
        List<TaskEquipemt> taskEquipemts = new ArrayList<>();
        service.findByMaintenanceTask(maintenanceTask).forEach(taskEquipemt -> {
            taskEquipemts.add(taskEquipemt);
        });
        jld.setTotal((long) taskEquipemts.size());
        jld.setRows(taskEquipemts);
        return jld;
    }
}
