package com.jrtech.templates.services;


import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;

import java.util.List;

/**
 * Created by jiangliang on 2016/6/2.菜单导航Service,elus
 */
public interface NavigationService {
   
    public List<GrantedAuthorityImpl> getNavigation();
}
