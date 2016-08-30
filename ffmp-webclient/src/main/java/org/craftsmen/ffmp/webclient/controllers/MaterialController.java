package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Material;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by suelmer on 2016/8/18.
 */

@RestController
@RequestMapping(value = "/rest/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private TaskRuntimeService runtimeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Material> mrrStandards = materialService.findAll(new CommonSpecs<Material>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        return jld;
    }

    @RequestMapping(value = "/findByMaintenanceTask", method = RequestMethod.POST)
    public JSONListData findByMaintenanceTask(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceTask maintenanceTask = runtimeService.findOne(parameters.getSearch());
        Page<Material> mrrStandards = materialService.findByMaintenanceTask(maintenanceTask, pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        return jld;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Material save(@RequestBody Material material) {
        String userName = userDetailsUtils.getCurrent().getUsername();
        material.setDelegate(accountService.findOneByName(userName));
        MaintenanceTask maintenanceTask = runtimeService.findOne(material.getMaintenanceTask().getId());
        material.setMaintenanceTask(maintenanceTask);
        return materialService.save(material);
    }
}
