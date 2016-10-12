package com.jrtech.templates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by jiangliang on 2016/10/10.
 */
@Component
public class AountAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private boolean forcePrincipalAsString = false;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        AountWebAuthenticationDetails details = (AountWebAuthenticationDetails) authentication.getDetails();  // 如上面的介绍，这里通过authentication.getDetails()获取详细信息
        // System.out.println(details); details.getRemoteAddress(); details.getSessionId(); details.getToken();
        // 下面是验证逻辑，验证通过则返回UsernamePasswordAuthenticationToken，
        // 否则，可直接抛出错误（AuthenticationException的子类，在登录验证不通过重定向至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息）
        if (null != details.getAgent()) {
            return passwordAuthenticationToken(authentication, userDetails);
        }
        String code = details.getToken();
        if (code.equals(details.getRawToken())) {
            return passwordAuthenticationToken(authentication, userDetails);
        } else {
            throw new BadCredentialsException("验证码错误！");
        }
    }

    private Authentication passwordAuthenticationToken(Authentication authentication, UserDetails userDetails) {
        if (new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            Object principalToReturn = userDetails;
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                    principalToReturn, authentication.getCredentials(),
                    authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
            result.setDetails(authentication.getDetails());
            return result;
        } else {
            throw new BadCredentialsException("密码错误！");
        }
    }

    public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
