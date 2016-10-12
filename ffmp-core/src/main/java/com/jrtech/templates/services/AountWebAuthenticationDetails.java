package com.jrtech.templates.services;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jiangliang on 2016/10/8.
 */
public class AountWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     *
     */
    private static final long serialVersionUID = 6975601077710753878L;
    private final String token;
    private final String rawToken;
    private final String agent;

    public AountWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("token");
        agent = request.getHeader("mobile");
        HttpSession session = request.getSession();
        rawToken= (String) session.getAttribute("PATCHCA");

    }

    public String getToken() {
        return token;
    }

    public String getAgent() {
        return agent;
    }

    public String getRawToken() {
        return rawToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Token: ").append(this.getToken());
        return sb.toString();
    }
}