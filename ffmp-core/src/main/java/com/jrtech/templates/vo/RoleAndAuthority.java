package com.jrtech.templates.vo;

/**
 * Created by jiangliang on 2016/6/27.角色权限绑定虚拟对象，elus
 */
public class RoleAndAuthority {
	/**
	 * 角色对象
	 */
    private String role;
    /**
     * 权限对象
     */
    private String anth;
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

    public String getAnth() {
        return anth;
    }

    public void setAnth(String anth) {
        this.anth = anth;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }
}
