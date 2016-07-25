package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.templates.services.AccountService;
import com.jrtech.templates.services.OrganizationService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.vo.Nodes;
import com.jrtech.templates.vo.OrganizationNode;

import com.jrtech.templates.vo.OrganizationPost;
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
    private final String LOAD_ERROR = "加载组织机构错误";
    private final String CREATE_ERROR = "创建组织机构错误";
    private final String UPDATE_ERROR = "修改组织机构错误";
    private final String DELETE_ERROR = "删除组织机构错误";

    /**
     * 加载组织机构列表
     *
     * @return 组织机构
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Nodes> findAllOrganization() {
        try {
            List<Nodes> roleNodes = new ArrayList<Nodes>();
            OrganizationNode organizationNode = new OrganizationNode(service.findRoot());
            roleNodes.add(organizationNode.getNodes());
            return roleNodes;
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }

    /**
     * 创建组织机构
     *
     * @param organization 组织机构
     * @return 组织机构
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Organization create(@RequestBody OrganizationPost organization) {
        try {
            Organization organization1 = organization.getOrganization();
            organization1.setParent(service.findOne(organization.getParentId()));
            if (!service.isDuplicateNameOnSameLevel(organization1)) {
                return service.save(organization1);
            } else {
                throw new ServiceException(CREATE_ERROR + "该组织机构已经存在");
            }
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }

    /**
     * 删除组织机构
     *
     * @param id id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrganization(@RequestParam("id") String id) {
        try {
            service.delete(id);
        } catch (ObjectRetrievalFailureException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        } catch (DataAccessException ex) {
            throw new ServiceException(DELETE_ERROR, ex);
        }
    }

    /**
     * 修改组织机构
     *
     * @param organization 组织机构
     * @return 组织机构
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Organization updateOrganization(@RequestBody OrganizationPost organization) {
        try {
            return service.save(organization.getOrganization());
        } catch (DataAccessException ex) {
            throw new ServiceException(UPDATE_ERROR, ex);
        }
    }
}
