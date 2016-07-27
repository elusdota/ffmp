package com.jrtech.ffmp.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消防设置维管标准
 * Created by suelmer on 2016/7/7.
 */
@Entity
@Table(name = "mrrstandard")
public class MrrStandard extends AbstractDomainObject{

    //编码
    private String code;
    //父级编码
    private  String  parentCode;
    //工作内容
    private String jobContent;
    //维管方式
    private String mrrMethod;
    //抽查比例
    private double proportion;
    //备注
    private String remark;

    //技术要求列表
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "mrrstandard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechniqueRequirements> list = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public List<TechniqueRequirements> getList() {
        return list;
    }

    public void setList(List<TechniqueRequirements> list) {
        this.list = list;
    }

    public String getMrrMethod() {
        return mrrMethod;
    }

    public void setMrrMethod(String mrrMethod) {
        this.mrrMethod = mrrMethod;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
