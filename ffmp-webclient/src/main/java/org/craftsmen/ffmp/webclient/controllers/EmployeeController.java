package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangliang on 2016/8/11.
 */
@RestController
@RequestMapping(value = "/rest/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private AccountService accountService;
    private Logger logger = LogManager.getLogger(EmployeeController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Employee> employees = service.findAll(new CommonSpecs<Employee>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(employees.getTotalElements());
        jld.setRows(employees.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载职工列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee) {
        if (null == accountService.findOneByName(employee.getCode())) {
            String password = new BCryptPasswordEncoder().encode("123456");
            Account account = new Account(employee.getCode(), password);
            accountService.save(account);
        } else {
            throw new ServiceException("该职工编码所创建的账户已经存在，无法利用该职工编码为其创建账户！");
        }
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建职工，职工名称--" + employee.getName());
        return service.save(employee);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) {
        Employee employee1 = service.findOne(employee.getId());
        employee1.setName(employee.getName());
        employee1.setCode(employee.getCode());
//        employee1.setCardid(employee.getCardid());
        employee1.setSex(employee.getSex());
        employee1.setPhone(employee.getPhone());
        employee1.setEmail(employee.getEmail());
        employee1.setRole(employee.getRole());
//        employee1.setCertificate(employee.getCertificate());
        employee1.setProfessional(employee.getProfessional());
        employee1.setDate(employee.getDate());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":修改职工，职工名称--" + employee.getName());
        return service.save(employee1);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Employee delete(@RequestParam("id") String id) {
        Employee employee = service.findOne(id);
        employee.setWork(false);
        return service.save(employee);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Employee get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    public Employee getByCode(@RequestParam("code") String code) {
        return service.findOneByCode(code);
    }
}
