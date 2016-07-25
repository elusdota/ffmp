package com.jrtech.templates.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/4/19.用户登录数据，elus
 */
@Service
public class UserDetailsUtils {
    private UserDetailsUtils() {
    }

    /**
     * 获取用户登录的UserDetails对象
     * @return UserDetails对象
     */
    public static UserDetails getCurrent() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null)
            return null;
        Authentication auth = securityContext.getAuthentication();
        if (auth == null || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken)
            return null;
        return (UserDetails) auth.getPrincipal();
    }

    /**
     *权限查询
     * @param pathName 权限路径
     * @return true该角色拥有此权限ޣ，false该角色没有此权限
     */
    public static boolean isAuthorized(String pathName) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null)
            return false;
        Authentication auth = securityContext.getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals(pathName.trim())) {
                return true;
            }
        }
        return false;
    }
}
