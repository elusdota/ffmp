package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 技术要求
 * Created by suelmer on 2016/7/17.
 */
@Entity
@Table(name = "techniqueRequirements")
public class TechniqueRequirements extends AbstractDomainObject{
    //名称为 强制，自定
    private String name;
    // 技术要求类型为 A/B/C/无
    private String type;
    //编号
    private String number;
    //描述
    private String description;
    //维管标准
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "mrrstandard_id", nullable = true)
    private MrrStandard mrrStandard;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MrrStandard getMrrStandard() {
        return mrrStandard;
    }

    public void setMrrStandard(MrrStandard mrrStandard) {
        this.mrrStandard = mrrStandard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
