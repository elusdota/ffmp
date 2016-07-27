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

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProjectSpecs<MaintenanceProject> maintenanceProjectSpecs = new MaintenanceProjectSpecs<MaintenanceProject>();
        maintenanceProjectSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername())));
        Page<MaintenanceProject> maintenanceProjects = service.findAll(maintenanceProjectSpecs.spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(maintenanceProjects.getTotalElements());
        jld.setRows(maintenanceProjects.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public MaintenanceProject create(@RequestBody MaintenanceProject maintenanceProject) {
        maintenanceProject.setCode(codeService.getMaintenanceProjectNum());
        Organization organization = organizationService.findOneByName(maintenanceProject.getDelegate().getName());
        maintenanceProject.setDelegate(organization);
        Customer customer = customerService.findOneByName(maintenanceProject.getCustomer().getName());
        maintenanceProject.setCustomer(customer);
        return service.save(maintenanceProject);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MaintenanceProject update(@RequestBody MaintenanceProject maintenanceProject) {
        MaintenanceProject maintenanceProject1 = service.findOne(maintenanceProject.getId());
        maintenanceProject1.getEquipments().addAll(maintenanceProject.getEquipments());
        maintenanceProject1.getEquipments().forEach(equipment -> {
            equipment.setOwner(maintenanceProject1);
            equipment.setCustomer(maintenanceProject1.getCustomer());
        });
        return service.save(maintenanceProject1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public MaintenanceProject get(@RequestParam("id") String id) {
        return service.findOne(id);
    }
}
