package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrtech.templates.services.NavigationService;
import com.jrtech.templates.services.ServiceException;

import java.util.List;

/**
 * �˵�����
 * Created by jiangliang on 2016/5/31.界面导航组件controller，࣬elus
 */
@RestController
@RequestMapping(value = "/rest/navigantion")
public class NavigationController {
    @Autowired
    private NavigationService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<GrantedAuthorityImpl> getRoot() {
        return service.getNavigation();
    }
}