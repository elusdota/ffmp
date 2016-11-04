package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消防设施维管标准
 * Created by suelmer on 2016/7/7.
 */
@Entity
@Table(name = "mrrstandard")
@JsonIgnoreProperties(value = { "parent" ,"inspection"})
public class MrrStandard extends AbstractTreeNode<MrrStandard>{
     public  MrrStandard(){
    }
    //编码
    private String code;
    //工作内容
    private String jobContent;
    //备注
    private String remark;

    //技术要求列表
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "mrrStandard", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<TechniqueRequirements> techniqueRequirementsList = new ArrayList<>();


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
