package com.jrtech.ffmp.data.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 生产厂家
 * Created by suelmer on 2016/7/29.
 */

@Entity
@Table(name = "Manufacturer")
public class Manufacturer extends AbstractNamedObject {

    //电话
    private String telphone;
    //联系人
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
