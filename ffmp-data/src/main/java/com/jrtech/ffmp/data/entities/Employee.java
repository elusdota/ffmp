package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jiangliang on 2016/8/11.
 */
@Entity
@Table(name = "Employee")
public class Employee extends AbstractNamedObject {
    @OneToOne(cascade = { CascadeType.ALL })
    private Account account;
    //性别
    private String sex;
//    //  身份证
//    private String cardid;
    //电话
    private String phone;
    //邮箱
    private String email;
    //职务
    private String role;
//    //证书
//    private String certificate;
    //编码
    private String code;
    //职称
    private String professional;
    //入职时间
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date date;
    //是否在职 true:在职
    private boolean work;
//    //证书类型
//    private String type;
    //学历
    private String diploma;
    //毕业学校
    private String school;
    //专业
    private String specializing;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

//    public String getCardid() {
//        return cardid;
//    }
//
//    public void setCardid(String cardid) {
//        this.cardid = cardid;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public String getCertificate() {
//        return certificate;
//    }
//
//    public void setCertificate(String certificate) {
//        this.certificate = certificate;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSpecializing() {
        return specializing;
    }

    public void setSpecializing(String specializing) {
        this.specializing = specializing;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
