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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private GrantedAuthorityService gaService;
   private Logger logger = LogManager.getLogger(RoleController.class.getName());

    /**
     * 加载组织机构下的角色
     *
     * @return Nodes对象
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Nodes> findAllRoles(@RequestBody @RequestParam("id") String id) {
        Account account = accountService.findOne(id);
        List<Nodes> roleNodes = new ArrayList<Nodes>();
        RoleNode roleNode = new RoleNode(organizationService.findRoot(), account);
        roleNodes.add(roleNode.getNodes());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载角色列表");
        return roleNodes;
    }

    /**
     * 创建角色
     *
     * @param roleAndOrganization 角色和组织机构对象
     * @return 角色对象
     */
    @RequestMapping(method = RequestMethod.POST)
    public Role create(@RequestBody RoleAndOrganization roleAndOrganization) {
        Organization organization = organizationService.findOne(roleAndOrganization.getOrganization().getId());
        Role role1 = new Role(roleAndOrganization.getRole().getName());
        role1.setOrganization(organization);
        if (!service.isDuplicateNameOnSameLevel(role1)) {
            logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建角色，名称--"+role1.getName());
            return service.save(role1);
        } else {
            throw new ServiceException("角色已经存在");
        }
    }

    /**
     * 删除角色
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteRole(@RequestParam("id") String id) {
        service.delete(id);
    }

    /**
     * �޸修改角色
     *
     * @param role 角色对象
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Role updateRole(@RequestBody Role role) {
        Role role1 = service.findOne(role.getId());
        role1.setName(role.getName());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":修改角色，名称--" + role1.getName());
        return service.save(role1);
    }

    /**
     * 根据id查询
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.GET)
    public Role findOne(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    /**
     * 根据权限路径获取boolean值 登录角色是否有该权限
     *
     * @param path 权限路径
     */
    @RequestMapping(value = "/getAnth", method = RequestMethod.GET)
    public boolean getAnth(@RequestParam("path") String path) {
        return UserDetailsUtils.isAuthorized(path);
    }

    @RequestMapping(value = "/allocationAuth", method = RequestMethod.POST)
    public synchronized Role allocationAuth(@RequestBody RoleAndAuthority roleAndAuthority) {
        Collection<GrantedAuthorityImpl> authorities = new TreeSet<>();
        Role role = service.findOne(roleAndAuthority.getRole());
        GrantedAuthorityImpl grantedAuthority = gaService.findOne(roleAndAuthority.getAnth());
        authorities.addAll(role.getAuthorities());
        role.getAuthorities().clear();
        if (!roleAndAuthority.isLift()) {
//            role.getAuthorities().remove(grantedAuthority);
            authorities.remove(grantedAuthority);
        } else {
            authorities.add(grantedAuthority);
//            role.getAuthorities().add(grantedAuthority);
        }
        role.getAuthorities().addAll(authorities);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":角色权限绑定，名称--"+role.getName());
        return service.save(role);
    }
}
