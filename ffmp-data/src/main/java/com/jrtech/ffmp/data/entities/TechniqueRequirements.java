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
    //检查内容为 强制，自定
    private String name;
    // 技术要求 性质类别为 A/B/C/无
    private String type;
    //编号
    private String number;
    //技术规范
    private String description;
    //维管方式
    private String mrrMethod;
    //抽查比例
    private double proportion;
    //使用年限
    private int lifetime;
    //检修年限
    private int changetime;
    //期限类型 根据使用年限还是更换年限计算设备到期时间
    private String maturity;
    //检查方法
    private String inspection;

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

    public int getChangetime() {
        return changetime;
    }

    public void setChangetime(int changetime) {
        this.changetime = changetime;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getMrrMethod() {
        return mrrMethod;
    }

    public void setMrrMethod(String mrrMethod) {
        this.mrrMethod = mrrMethod;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }
}
