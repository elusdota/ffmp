package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.templates.services.EmployeeService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangliang on 2016/8/11.
 */
@RestController
@RequestMapping(value = "/rest/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Employee> employees = service.findAll(new CommonSpecs<Employee>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(employees.getTotalElements());
        jld.setRows(employees.getContent());
        return jld;
    }
    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee) {
        return service.save(employee);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) {
        Employee employee1=service.findOne(employee.getId());
        employee1.setName(employee.getName());
        employee1.setCode(employee.getCode());
        employee1.setCardid(employee.getCardid());
        employee1.setSex(employee.getSex());
        employee1.setPhone(employee.getPhone());
        employee1.setEmail(employee.getEmail());
        employee1.setRole(employee.getRole());
        employee1.setCertificate(employee.getCertificate());
        employee1.setProfessional(employee.getProfessional());
        employee1.setDate(employee.getDate());
        return service.save(employee1);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Employee delete(@RequestParam("id") String id) {
        Employee employee=service.findOne(id);
        employee.setWork(false);
        return service.save(employee);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Employee get(@RequestParam("id") String id) {
        return service.findOne(id);
    }
}
