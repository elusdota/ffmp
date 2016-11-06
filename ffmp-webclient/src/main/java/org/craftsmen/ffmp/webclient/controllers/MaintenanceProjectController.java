package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Customer;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.MaintenanceProjectEquipmentVo;
import com.jrtech.templates.vo.MaintenanceProjectSpecs;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/15.项目控制器，elus
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

    private Logger logger = LogManager.getLogger(MaintenanceProjectController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProjectSpecs<MaintenanceProject> maintenanceProjectSpecs = new MaintenanceProjectSpecs<MaintenanceProject>();
        maintenanceProjectSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(UserDetailsUtils.getCurrent().getUsername())));
        Page<MaintenanceProject> maintenanceProjects = service.findAll(maintenanceProjectSpecs.spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(maintenanceProjects.getTotalElements());
        jld.setRows(maintenanceProjects.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载项目列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public MaintenanceProject create(@RequestBody MaintenanceProject maintenanceProject) {
        maintenanceProject.setCode(codeService.getMaintenanceProjectNum());
        Organization organization = organizationService.findOne(maintenanceProject.getDelegate().getId());
        if(organization==null){
            throw new ServiceException("维保小组不存在，请重新输入！");
        }
        maintenanceProject.setDelegate(organization);
        Customer customer = customerService.findOne(maintenanceProject.getCustomer().getId());
        if(customer==null){
            throw new ServiceException("客户不存在，请重新输入！");
        }
        if(maintenanceProject.getDays()<0||maintenanceProject.getDays()>=28){
            throw new ServiceException("巡检日期必须大于0，小于等于28！");
        }
        maintenanceProject.setTerminate(false);
        maintenanceProject.setCustomer(customer);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建项目，项目编号---"+maintenanceProject.getCode());
        return service.save(maintenanceProject);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MaintenanceProject update(@RequestBody MaintenanceProjectEquipmentVo maintenanceProject) {
        MaintenanceProject maintenanceProject1 = service.findOne(maintenanceProject.getId());
//        final int[] i = {0};
//        i[0] = maintenanceProject1.getEquipments().size();
        maintenanceProject.getEquipments().forEach(equipment1 -> {
//            i[0] = i[0] + 1;
//            int t = i[0] - 1;
//            String code = getLastSixNum("" + t, 3);
//            equipment1.setCode(getCodeNum(maintenanceProject1.getCode(), 4) +
////                    mrrStandardService.findOneByName(equipment1.getTypemax()).getCode() +
//                    mrrStandardService.findOneByName(equipment1.getTypemin()).getCode() + code);
            equipment1.setOwner(maintenanceProject1);
            equipment1.setCustomer(maintenanceProject1.getCustomer());
        });
        maintenanceProject1.getEquipments().addAll(maintenanceProject.getEquipments());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":绑定项目设备，项目编号---" + maintenanceProject1.getCode());
        return service.save(maintenanceProject1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public MaintenanceProject get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/findByNameLike", method = RequestMethod.GET)
    public List<MaintenanceProject> findByNameLike(@RequestParam("name") String name) {
        logger.info(UserDetailsUtils.getCurrent().getUsername()+":查询项目，项目关键字--"+name);
        return service.findByNameLike("%" + name + "%");
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public MaintenanceProject delete(@RequestParam("id") String id) {
        MaintenanceProject maintenanceProject = service.findOne(id);
        maintenanceProject.setTerminate(true);
        return service.save(maintenanceProject);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public MaintenanceProject updateCommon(@RequestBody MaintenanceProject maintenanceProject) {
        MaintenanceProject maintenanceProject1=service.findOne(maintenanceProject.getId());
        maintenanceProject1.setAddress(maintenanceProject.getAddress());
        maintenanceProject1.setArea(maintenanceProject.getArea());
        maintenanceProject1.setDays(maintenanceProject.getDays());
        maintenanceProject1.setFloors(maintenanceProject.getFloors());
        maintenanceProject1.setNature(maintenanceProject.getNature());
        maintenanceProject1.setManager(maintenanceProject.getManager());
        maintenanceProject1.setManagerTelephone(maintenanceProject.getManagerTelephone());
        maintenanceProject1.setTotalHeight(maintenanceProject.getTotalHeight());
        maintenanceProject1.setInputDate(maintenanceProject.getInputDate());
        return service.save(maintenanceProject1);
    }
}

