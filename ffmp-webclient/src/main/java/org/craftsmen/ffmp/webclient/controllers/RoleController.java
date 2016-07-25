package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.templates.services.AccountService;
import com.jrtech.templates.services.GrantedAuthorityService;
import com.jrtech.templates.services.OrganizationService;
import com.jrtech.templates.services.RoleService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.Nodes;
import com.jrtech.templates.vo.RoleAndAuthority;
import com.jrtech.templates.vo.RoleAndOrganization;
import com.jrtech.templates.vo.RoleNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by jiangliang on 2016/6/21.elus 角色controller
 */
@RestController
@RequestMapping(value = "/rest/role")
public class RoleController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RoleService service;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @Autowired
    private GrantedAuthorityService gaService;
    private final String LOAD_ERROR = "加载数据错误";
    private final String CREATE_ERROR = "创建角色错误";
    private final String UPDATE_ERROR = "修改角色错误";
    private final String DELETE_ERROR = "删除角色错误";

    /**
     * 加载组织机构下的角色
     *
     * @return Nodes对象
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Nodes> findAllRoles(@RequestBody @RequestParam("id") String id) {
        try {
            Account account = accountService.findOne(id);
            List<Nodes> roleNodes = new ArrayList<Nodes>();
            RoleNode roleNode = new RoleNode(organizationService.findRoot(), account);
            roleNodes.add(roleNode.getNodes());
            return roleNodes;
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }

    /**
     * 创建角色
     *
     * @param roleAndOrganization 角色和组织机构对象
     * @return 角色对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Role create(@RequestBody RoleAndOrganization roleAndOrganization) {
        try {
            Organization organization = organizationService.findOne(roleAndOrganization.getOrganization().getId());
            Role role1 = new Role(roleAndOrganization.getRole().getName());
            role1.setOrganization(organization);
            if (!service.isDuplicateNameOnSameLevel(role1)) {
                return service.save(role1);
            } else {
                throw new ServiceException(CREATE_ERROR + "角色已经存在");
            }
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }

    /**
     * 删除角色
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@RequestParam("id") String id) {
        try {
            service.delete(id);
        } catch (ObjectRetrievalFailureException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        } catch (DataAccessException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        }
    }

    /**
     * �޸修改角色
     *
     * @param role 角色对象
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@RequestBody Role role) {
        try {
            Role role1 = service.findOne(role.getId());
            role1.setName(role.getName());
            return service.save(role1);
        } catch (DataAccessException ex) {
            throw new ServiceException(UPDATE_ERROR, ex);
        }
    }

    /**
     * 根据id查询
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Role findOne(@RequestParam("id") String id) {
        try {
            return service.findOne(id);
        } catch (ObjectRetrievalFailureException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        } catch (DataAccessException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        }
    }

    /**
     * 根据权限路径获取boolean值 登录角色是否有该权限
     *
     * @param path 权限路径
     */
    @RequestMapping(value = "/getAnth", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean getAnth(@RequestParam("path") String path) {
        try {
            return userDetailsUtils.isAuthorized(path);
        } catch (ObjectRetrievalFailureException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        } catch (DataAccessException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        }
    }

    @RequestMapping(value = "/allocationAuth",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Role allocationAuth(@RequestBody RoleAndAuthority roleAndAuthority) {
        try {
            Collection<GrantedAuthorityImpl> authorities = new TreeSet<>();
            Role role = service.findOne(roleAndAuthority.getRole());
            GrantedAuthorityImpl grantedAuthority = gaService.findOne(roleAndAuthority.getAnth());
            authorities.addAll(role.getAuthorities());
            role.getAuthorities().clear();
            if (!roleAndAuthority.isLift()) {
                authorities.remove(grantedAuthority);
            } else {
                authorities.add(grantedAuthority);
            }
            role.getAuthorities().addAll(authorities);
            return service.save(role);
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }
}
