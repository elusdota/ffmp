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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @Autowired
    private MrrStandardService mrrStandardService;

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
        if(organization==null){
            throw new ServiceException("维保小组不存在，请重新输入！");
        }
        maintenanceProject.setDelegate(organization);
        Customer customer = customerService.findOneByName(maintenanceProject.getCustomer().getName());
        if(customer==null){
            throw new ServiceException("客户不存在，请重新输入！");
        }
        maintenanceProject.setCustomer(customer);
        return service.save(maintenanceProject);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MaintenanceProject update(@RequestBody MaintenanceProjectEquipmentVo maintenanceProject) {
        MaintenanceProject maintenanceProject1 = service.findOne(maintenanceProject.getId());
        final int[] i = {0};
        i[0] = maintenanceProject1.getEquipments().size();
        maintenanceProject.getEquipments().forEach(equipment1 -> {
            i[0] = i[0] + 1;
            int t = i[0] - 1;
            String code = getLastSixNum("" + t, 3);
            equipment1.setCode(getCodeNum(maintenanceProject1.getCode(), 4) +
                    mrrStandardService.findOneByName(equipment1.getTypemax()).getCode() +
                    mrrStandardService.findOneByName(equipment1.getTypemin()).getCode() + code);
            equipment1.setOwner(maintenanceProject1);
            equipment1.setCustomer(maintenanceProject1.getCustomer());
        });
        maintenanceProject1.getEquipments().addAll(maintenanceProject.getEquipments());
        return service.save(maintenanceProject1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public MaintenanceProject get(@RequestParam("id") String id) {
        return service.findOne(id);
    }
    public String getLastSixNum(String s,int v) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < v; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j + 1, "0");
        }
        return rs;
    }
    public String getCodeNum(String s,int v) {
        String rs = s;
        int i = Integer.parseInt(rs);
        rs = "" + i;
        for (int j = rs.length(); j < v; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j, "0");
        }
        return rs;
    }
}
