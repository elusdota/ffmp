package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import com.jrtech.ffmp.data.entities.Role;
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

import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/23.客户控制器,elus
 */
@RestController
@RequestMapping(value = "/rest/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GrantedAuthorityService grantedAuthorityService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(CustomerController.class.getName());
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Customer> customers = service.findAll(new CommonSpecs<Customer>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(customers.getTotalElements());
        jld.setRows(customers.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载客户列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer) {
        Role role = roleService.findOneByName("customer");
        if (role == null) {
            role = new Role("customer");
            role.getAuthorities().add(grantedAuthorityService.findOneByName("维管工作管理"));
            role.getAuthorities().add(grantedAuthorityService.findOneByName("项目信息管理"));
            role.getAuthorities().add(grantedAuthorityService.findOneByName("查看项目详细信息"));
            role.getAuthorities().add(grantedAuthorityService.findOneByName("历史任务查询"));
            role.getAuthorities().add(grantedAuthorityService.findOneByName("历史任务"));
            role.getAuthorities().add(grantedAuthorityService.findOneByName("查看历史任务"));
            role.setOrganization(organizationService.findRoot());
            roleService.save(role);
        }
        Account account = new Account("C"+codeService.getCustomerNum(), new BCryptPasswordEncoder().encode(customer.getTelephone()));
        account.getRoles().add(role);
        accountService.save(account);
        customer.setAccount(account);//
        customer.setCode(codeService.getCustomerNum());
        customer.setCreateTime(new Date());
        if (service.isDuplicateNameOnSameLevel(customer)) {
            logger.error( UserDetailsUtils.getCurrent().getUsername()+":创建客户失败,客户名称--"+customer.getName());
            throw new ServiceException("该客户已经存在！");
        }
        logger.info(UserDetailsUtils.getCurrent().getUsername()+":创建客户，客户名称--"+customer.getName());
        return service.save(customer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer) {
        Customer customer1 = service.findOne(customer.getId());
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customer1.setContect(customer.getContect());
        customer1.setEmail(customer.getEmail());
        customer1.setTaxId(customer.getTaxId());
        customer1.setTelephone(customer.getTelephone());
        customer1.setType(customer.getType());
        customer1.setBank(customer.getBank());
        customer1.setBankAccount(customer.getBankAccount());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":修改客户，客户名称--" + customer.getName());
        return service.save(customer1);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") String id) {
        service.delete(id);
    }

    @RequestMapping(value = "/findByNameLike", method = RequestMethod.GET)
    public List<Customer> findByNameLike(@RequestParam("name") String name) {
        logger.info(UserDetailsUtils.getCurrent().getUsername()+":查询客户，客户关键字--"+name);
        return service.findByNameLike("%" + name + "%");
    }
}
