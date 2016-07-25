package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/1.
 */
@Service
public class NavigationServiceImpl implements NavigationService {
    @Autowired
    private GrantedAuthorityService service;
    @Autowired
    private UserDetailsUtils authorization;
    @Override
    public List<GrantedAuthorityImpl> getNavigation() {
        final List<GrantedAuthorityImpl> grantedAuthorities = new ArrayList<GrantedAuthorityImpl>();
        final List<GrantedAuthorityImpl> grantedAuthorities1 = service.findRoot();
        grantedAuthorities1.forEach(grantedAuthoritie -> {
            if (authorization.isAuthorized(grantedAuthoritie.getAuthority())) {
                List<GrantedAuthorityImpl> grantedAuthorities2 = new ArrayList<GrantedAuthorityImpl>();
                grantedAuthoritie.getChildren().forEach(anth -> {
                    if (authorization.isAuthorized(anth.getAuthority())) {
                        if (anth.getChildren().size() > 0) {
                            grantedAuthorities2.add(anth);
                        }
                    }
                });
                grantedAuthoritie.getChildren().clear();
                grantedAuthoritie.getChildren().addAll(grantedAuthorities2);
                grantedAuthorities.add(grantedAuthoritie);
            }
        });
        return grantedAuthorities;
    }
}
