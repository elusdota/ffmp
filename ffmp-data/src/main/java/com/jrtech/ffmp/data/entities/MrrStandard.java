package com.jrtech.ffmp.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消防设施维管标准
 * Created by suelmer on 2016/7/7.
 */
@Entity
@Table(name = "mrrstandard")
public class MrrStandard extends AbstractDomainObject{

    //设施名称
    private String name;
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
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "mrrStandard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechniqueRequirements> techniqueRequirementsList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<TechniqueRequirements> getTechniqueRequirementsList() {
        return techniqueRequirementsList;
    }

    public void setTechniqueRequirementsList(List<TechniqueRequirements> techniqueRequirementsList) {
        this.techniqueRequirementsList = techniqueRequirementsList;
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
