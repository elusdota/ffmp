package com.jrtech.templates.vo;

/**
 * Created by jiangliang on 2016/6/27.账户角色绑定虚拟对象
 */
public class AccountAndRole {
	/**
	 * 角色
	 */
    private String role;
    /**
     * 账户
     */
    private String account;
    /**
     * 是否绑定
     */
    private boolean lift;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }
}
