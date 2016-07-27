package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.RepairForm;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.RepairFormSpecs;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by jiangliang on 2016/7/25.
 */
@RestController
@RequestMapping(value = "/rest/repairForm")
public class RepairFormController {
    @Autowired
    private RepairFormService service;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    @Autowired
    private CodeService codeService;
    private final String LOAD_ERROR = "加载数据错误";
    private final String CREATE_ERROR = "创建报修单错误";

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JSONListData findHistoryTask(@RequestBody TableGetDataParameters parameters) {
            RepairFormSpecs<RepairForm> repairFormSpecs = new RepairFormSpecs<RepairForm>();
            repairFormSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername())));
            PageableImpl pageable = new PageableImpl(parameters);
            Page<RepairForm> repairForms = service.findAll(repairFormSpecs.spec(parameters), pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(repairForms.getTotalElements());
            jld.setRows(repairForms.getContent());
            return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public RepairForm create(@RequestBody RepairForm repairForm) {
            if (maintenanceProjectService.findOneByCode(repairForm.getProjectNumber())==null){
                throw new ServiceException("无此项目编号的项目！请重新输入项目编号。");
            }
            repairForm.setCode(codeService.getRepairFormNum());
            repairForm.setDate(new Date());
            repairForm.setAccount(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername()));
            return service.save(repairForm);
    }
}
