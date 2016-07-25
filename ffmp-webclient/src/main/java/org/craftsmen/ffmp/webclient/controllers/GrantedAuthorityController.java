package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.templates.services.GrantedAuthorityService;
import com.jrtech.templates.services.NavigationService;
import com.jrtech.templates.services.RoleService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.vo.GrantedAuthorityNode;
import com.jrtech.templates.vo.Nodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/25.
 */
@RestController
@RequestMapping(value = "/rest/grantedAuthority")
public class GrantedAuthorityController {
    @Autowired
    private GrantedAuthorityService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NavigationService navigationService;
    private final String LOAD_ERROR = "加载权限错误";
    private final String CREATE_ERROR = "创建权限错误";
    private final String UPDATE_ERROR = "修改权限错误";
    private final String DELETE_ERROR = "删除权限错误";

    /**
     * 加载组织机构列表
     *
     * @return 组织机构
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Nodes> findAllRoles(@RequestParam("id") String id) {
        try {
            Role role=roleService.findOne(id);
            List<Nodes> nodes = new ArrayList<Nodes>();
            List<GrantedAuthorityImpl> grantedAuthorities = service.findRoot();
            grantedAuthorities.forEach(grantedAuthority -> {
                GrantedAuthorityNode grantedAuthorityNode = new GrantedAuthorityNode(grantedAuthority, role);
                nodes.add(grantedAuthorityNode.getNodes());
            });
            return nodes;
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }
}
