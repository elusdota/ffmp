package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.RepairForm;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.RepairFormSpecs;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by jiangliang on 2016/7/25.报修单控制器，elus
 */
@RestController
@RequestMapping(value = "/rest/repairForm")
public class RepairFormController {
    @Autowired
    private RepairFormService service;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    private Logger logger = LogManager.getLogger(RepairFormController.class.getName());
    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findHistoryTask(@RequestBody TableGetDataParameters parameters) {
        RepairFormSpecs<RepairForm> repairFormSpecs = new RepairFormSpecs<RepairForm>();
        repairFormSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(UserDetailsUtils.getCurrent().getUsername())));
        PageableImpl pageable = new PageableImpl(parameters);
        Page<RepairForm> repairForms = service.findAll(repairFormSpecs.spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(repairForms.getTotalElements());
        jld.setRows(repairForms.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载报修单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.GET)
    public RepairForm get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/findStateFalse", method = RequestMethod.POST)
    public JSONListData findState(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<RepairForm> repairForms = service.findByProcessing(false, pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(repairForms.getTotalElements());
        jld.setRows(repairForms.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载报修单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public RepairForm create(@RequestBody RepairForm repairForm) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(repairForm.getProjectNumber());
        if (maintenanceProject == null) {
            throw new ServiceException("无此项目编号的项目！请重新输入项目编号。");
        }
        repairForm.setProjectNumber(maintenanceProject.getName());
        repairForm.setCode(codeService.getRepairFormNum());
        repairForm.setDate(new Date());
        repairForm.setAccount(accountService.findOneByName(UserDetailsUtils.getCurrent().getUsername()));
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建报修单，编号--" + repairForm.getCode());
        return service.save(repairForm);
    }
}
