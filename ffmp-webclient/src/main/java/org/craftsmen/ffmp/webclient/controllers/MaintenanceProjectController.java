package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Customer;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.MaintenanceProjectSpecs;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangliang on 2016/7/15.
 */
@RestController
@RequestMapping(value = "/rest/maintenanceProject")
public class MaintenanceProjectController {
    @Autowired
    private MaintenanceProjectService service;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    private final String LOAD_ERROR = "加载数据错误";
    private final String CREATE_ERROR = "创建项目错误";
    private final String UPDATE_ERROR = "修改项目错误";

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        try {
            PageableImpl pageable = new PageableImpl(parameters);
            MaintenanceProjectSpecs<MaintenanceProject> maintenanceProjectSpecs = new MaintenanceProjectSpecs<MaintenanceProject>();
            maintenanceProjectSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername())));
            Page<MaintenanceProject> maintenanceProjects = service.findAll(maintenanceProjectSpecs.spec(parameters), pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(maintenanceProjects.getTotalElements());
            jld.setRows(maintenanceProjects.getContent());
            return jld;
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceProject create(@RequestBody MaintenanceProject maintenanceProject) {
        try {
            maintenanceProject.setCode(codeService.getMaintenanceProjectNum());
            Organization organization = organizationService.findOneByName(maintenanceProject.getDelegate().getName());
            maintenanceProject.setDelegate(organization);
            Customer customer = customerService.findOneByName(maintenanceProject.getCustomer().getName());
            maintenanceProject.setCustomer(customer);
            return service.save(maintenanceProject);
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceProject update(@RequestBody MaintenanceProject maintenanceProject) {
        try {
            MaintenanceProject maintenanceProject1 = service.findOne(maintenanceProject.getId());
            maintenanceProject1.getEquipments().addAll(maintenanceProject.getEquipments());
            maintenanceProject1.getEquipments().forEach(equipment -> {
                equipment.setOwner(maintenanceProject1);
                equipment.setCustomer(maintenanceProject1.getCustomer());
            });
            return service.save(maintenanceProject1);
        } catch (DataAccessException ex) {
            throw new ServiceException(UPDATE_ERROR, ex);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceProject get(@RequestParam("id") String id) {
        try {
            return service.findOne(id);
        } catch (DataAccessException ex) {
            throw new ServiceException(LOAD_ERROR, ex);
        }
    }
}
