package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.AccountAndRole;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.PasswordVo;
import com.jrtech.templates.vo.TableGetDataParameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiangliang on 2016/6/21.elus 账户Controller
 */
@RestController
@RequestMapping(value = "/rest/account")
public class AccountController {
    @Autowired
    private AccountService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    static final Logger logger = LogManager.getLogger(AccountController.class.getName());

    /**
     * 加载账户列表
     *
     * @return 账户列表
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        if (parameters.getSearch() == null) {
            parameters.setSearch("");
        }
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Account> accounts = service.findByNameContaining(parameters.getSearch()+"%", pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(accounts.getTotalElements());
        jld.setRows(accounts.getContent());
        logger.info(userDetailsUtils.getCurrent().getUsername()+":加载账户列表");
        return jld;
    }

    /**
     * 创建账户
     *
     * @param account 账户
     * @return 账户
     */
    @RequestMapping(method = RequestMethod.POST)
    public Account create(@RequestBody Account account) {
        if (service.findOneByName(account.getName()) == null) {
            String password = new BCryptPasswordEncoder().encode("123456");
            account.setPassword(password);
            logger.info(userDetailsUtils.getCurrent().getUsername() + ":创建账户，账户名称--" + account.getName());
            return service.save(account);
        } else {
            logger.error( userDetailsUtils.getCurrent().getUsername()+":创建账户失败，账户名称--"+account.getName());
            throw new ServiceException("创建账户失败，账户已经存在！");
        }
    }

    /**
     * 删除
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAccount(@RequestParam("id") String id) {
        logger.info(userDetailsUtils.getCurrent().getUsername() + ":删除账户，账户id--" + id);
        service.delete(id);
    }

    /**
     * 获取登录用户
     *
     * @return 账户对象
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Account getLoginAccount() {
        return service.findOneByName(userDetailsUtils.getCurrent().getUsername());
    }

    /**
     * 修改账户
     *
     * @param passwordVo 账户密码对比对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Account updateAccount(@RequestBody PasswordVo passwordVo) {
        Account account1 = service.findOne(passwordVo.getAccount().getId());
        String password1 = new BCryptPasswordEncoder().encode(passwordVo.getAccount().getPassword());
        if (new BCryptPasswordEncoder().matches(passwordVo.getRawpassword(), account1.getPassword())) {
            account1.setPassword(password1);
            logger.info(userDetailsUtils.getCurrent().getUsername() + ":修改账户，账户名称--" + account1.getName());
            return service.save(account1);
        } else {
            logger.error(userDetailsUtils.getCurrent().getUsername() + ":修改账户失败，账户名称--" + account1.getName());
            throw new ServiceException("原密码输入错误！请重新输入。");
        }
    }

    @RequestMapping(value = "/allocationRole", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Account allocationRole(@RequestBody AccountAndRole accountAndRole) {
        Collection<Role> roles = new ArrayList<>();
        Account account = service.findOne(accountAndRole.getAccount());
        Role role = roleService.findOne(accountAndRole.getRole());
        roles.addAll(account.getRoles());
        account.getRoles().clear();
        if (!accountAndRole.isLift()) {
            roles.remove(role);
        } else {
            roles.add(role);
        }
        logger.info(userDetailsUtils.getCurrent().getUsername() + ":修改账户角色，账户名称--" + account.getName());
        account.getRoles().addAll(roles);
        return service.save(account);
    }
}
