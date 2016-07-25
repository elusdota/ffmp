package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.templates.services.AccountService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.RoleService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.vo.AccountAndRole;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectRetrievalFailureException;
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
    private final String LOAD_ERROR = "数据加载错误";
    private final String CREATE_ERROR = "创建账户错误";
    private final String UPDATE_ERROR = "修改账户错误";
    private final String DELETE_ERROR = "删除账户错误";

    /**
     * 加载账户列表
     *
     * @return 账户列表
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        try {
            if (parameters.getSearch() == null) {
                parameters.setSearch("");
            }
            PageableImpl pageable = new PageableImpl(parameters);
            Page<Account> accounts = service.findByNameContaining(parameters.getSearch(), pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(accounts.getTotalElements());
            jld.setRows(accounts.getContent());
            return jld;
        } catch (DataAccessException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }

    /**
     * 创建账户
     *
     * @param account 账户
     * @return 账户
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Account create(@RequestBody Account account) {
        try {
            if (service.findOneByName(account.getName()) == null) {
                account.setPassword("123456");
                return service.save(account);
            } else {
                throw new ServiceException(CREATE_ERROR + "，账户已经存在！");
            }
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }

    /**
     * 删除
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestParam("id") String id) {
        try {
            service.delete(id);
        } catch (ObjectRetrievalFailureException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        } catch (DataAccessException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        }
    }

    /**
     * 修改账户
     *
     * @param account 账户
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@RequestBody Account account) {
        try {
            Account account1 = service.findOne(account.getId());
            account1.setPassword(account.getPassword());
            return service.save(account1);
        } catch (DataAccessException ex) {
            throw new ServiceException(UPDATE_ERROR, ex);
        }
    }

    @RequestMapping(value = "/allocationRole", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Account allocationRole(@RequestBody AccountAndRole accountAndRole) {
        try {
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
            account.getRoles().addAll(roles);
            return service.save(account);
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }
}
