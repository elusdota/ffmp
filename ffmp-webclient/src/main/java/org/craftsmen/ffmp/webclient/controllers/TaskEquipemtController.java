package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import com.jrtech.templates.services.EquipmentService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.TaskEquipemtService;
import com.jrtech.templates.services.TaskRuntimeService;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import com.jrtech.templates.vo.TaskEquipemtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(value = "/findByMaintenanceTask",method = RequestMethod.POST)
    public JSONListData findByMaintenanceTask(@RequestBody TableGetDataParameters parameters) {
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(parameters.getSearch());
        PageableImpl pageable = new PageableImpl(parameters);
        Page<TaskEquipemt> taskEquipemts = service.findByMaintenanceTask(maintenanceTask,pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(taskEquipemts.getTotalElements());
        jld.setRows(taskEquipemts.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskEquipemt save(@RequestBody TaskEquipemtVO taskEquipemtVO){
        TaskEquipemt taskEquipemt = new TaskEquipemt();
        taskEquipemt.setDescription(taskEquipemtVO.getDescription());
        taskEquipemt.setEquipment(equipmentService.findOneByCode(taskEquipemtVO.getEquipmentCode()));
        taskEquipemt.setMaintenanceTask(taskRuntimeService.findOne(taskEquipemtVO.getMaintenanceTaskId()));
        return service.save(taskEquipemt);
    }

}
