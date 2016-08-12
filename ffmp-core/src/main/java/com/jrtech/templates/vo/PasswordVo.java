package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jiangliang on 2016/8/11.
 */
public class PasswordVo extends Account{
    private Account account;
    private String rawpassword;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getRawpassword() {
        return rawpassword;
    }

    public void setRawpassword(String rawPassword) {
        this.rawpassword = rawPassword;
    }
}
