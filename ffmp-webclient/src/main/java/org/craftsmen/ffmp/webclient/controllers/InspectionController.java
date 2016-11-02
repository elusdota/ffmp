package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.MaintenanceProjectInspectionVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiangliang on 2016/11/2.
 */
@RestController
@RequestMapping(value = "/rest/inspections")
public class InspectionController {
    @Autowired
    private InspectionService service;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    private Logger logger = LogManager.getLogger(AccountController.class.getName());

    @RequestMapping(method = RequestMethod.POST)
    public MaintenanceProject create(@RequestBody MaintenanceProjectInspectionVo maintenanceProjectInspectionVo) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(maintenanceProjectInspectionVo.getId());
        maintenanceProject.getInspections().clear();
        maintenanceProjectInspectionVo.getInspections().forEach(inspection1 -> {
            inspection1.setMaintenanceProject(maintenanceProject);
            maintenanceProject.getInspections().add(service.save(inspection1));
        });
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建项目巡检标准，项目编号--" + maintenanceProject.getCode());
        return maintenanceProjectService.save(maintenanceProject);
    }
    @RequestMapping(value = "/quarter",method = RequestMethod.GET)
    public Inspection quarter(@RequestParam("id") String id) {
        return getInspection(id,"季度巡检");
    }
    @RequestMapping(value = "/month",method = RequestMethod.GET)
    public Inspection month(@RequestParam("id") String id) {
        return getInspection(id, "月度巡检");
    }
    @RequestMapping(value = "/year",method = RequestMethod.GET)
    public Inspection year(@RequestParam("id") String id) {
        return getInspection(id, "年度巡检");
    }
    public Inspection getInspection(String id,String name){
        Inspection inspection=service.findByMaintenanceProjectAndName(maintenanceProjectService.findOne(id),name);
            return inspection;
    }
}
