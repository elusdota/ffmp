package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.templates.services.AccountService;
import com.jrtech.templates.services.OrganizationService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.Nodes;
import com.jrtech.templates.vo.OrganizationNode;

import com.jrtech.templates.vo.OrganizationPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/21.elus
 */
@RestController
@RequestMapping(value = "/rest/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService service;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    static final Logger logger = LogManager.getLogger(OrganizationController.class.getName());

    /**
     * 加载组织机构列表
     *
     * @return 组织机构
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Nodes> findAllOrganization() {
        List<Nodes> roleNodes = new ArrayList<Nodes>();
        OrganizationNode organizationNode = new OrganizationNode(service.findRoot());
        roleNodes.add(organizationNode.getNodes());
        logger.info(userDetailsUtils.getCurrent().getUsername() + ":加载组织机构列表");
        return roleNodes;
    }

    /**
     * 创建组织机构
     *
     * @param organization 组织机构
     * @return 组织机构
     */
    @RequestMapping(method = RequestMethod.POST)
    public Organization create(@RequestBody OrganizationPost organization) {
        Organization organization1 = organization.getOrganization();
        organization1.setParent(service.findOne(organization.getParentId()));
        if (!service.isDuplicateNameOnSameLevel(organization1)) {
            logger.info(userDetailsUtils.getCurrent().getUsername() + ":创建组织机构，名称---"+organization.getOrganization().getName());
            return service.save(organization1);
        } else {
            throw new ServiceException("该组织机构已经存在");
        }
    }

    /**
     * 删除组织机构
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteOrganization(@RequestParam("id") String id) {
        service.delete(id);
    }

    /**
     * 修改组织机构
     *
     * @param organization 组织机构
     * @return 组织机构
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Organization updateOrganization(@RequestBody Organization organization) {
        service.findOne(organization.getId());
        organization.setParent(service.findOne(organization.getId()).getParent());
        logger.info(userDetailsUtils.getCurrent().getUsername() + ":修改组织机构，名称---"+organization.getName());
        return service.save(organization);
    }
}
