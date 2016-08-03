package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.templates.services.EquipmentService;
import com.jrtech.templates.services.MaintenanceProjectService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangliang on 2016/7/27.设备操作控制器,elus
 */
@RestController
@RequestMapping(value = "/rest/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService service;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    @RequestMapping(value = "/findProject", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProject maintenanceProject=maintenanceProjectService.findOne(parameters.getSearch());
        Page<Equipment> equipments = service.findByOwner(maintenanceProject,pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipments.getContent());
        return jld;
    }
}
