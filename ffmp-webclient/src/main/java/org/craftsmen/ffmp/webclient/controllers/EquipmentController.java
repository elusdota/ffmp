package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.templates.services.EquipmentService;
import com.jrtech.templates.services.MaintenanceProjectService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private Logger logger = LogManager.getLogger(EquipmentController.class.getName());

    @RequestMapping(value = "/findProject", method = RequestMethod.POST)
    public JSONListData findProject(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(parameters.getSearch());
        Page<Equipment> equipments = service.findByOwner(maintenanceProject, pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipments.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载项目设备列表");
        return jld;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Equipment> equipments = service.findAll(new CommonSpecs<Equipment>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipments.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载设备列表");
        return jld;
    }
    @RequestMapping(value = "/findAllList", method = RequestMethod.GET)
    public List<Equipment> findAllList(@RequestParam("projectId") String projectId) {
        MaintenanceProject maintenanceProject=maintenanceProjectService.findOne(projectId);
       return service.findByOwner(maintenanceProject);
    }

    /**
     * 扫描条码 通过条码返回设备信息
     * @param code
     * @return 设备信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public Equipment get(@RequestParam("code") String code) {
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":扫码获取设备信息，编码--"+code);
        return service.findOneByCode(code);
    }
}
