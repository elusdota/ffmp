package com.jrtech.ffmp.data.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 重点部位维管标准
 * Created by suelmer on 2016/7/7.
 */
@Entity
@Table(name = "KeyPartStandard")
public class KeyPartStandard extends AbstractNamedObject{

    //维护管理内容
    private String mrrContent;
    //维管方式
    private String mrrMethod;
    //抽查比例
    private double proportion;
    //修改控制
    private String control;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getMrrContent() {
        return mrrContent;
    }

    public void setMrrContent(String mrrContent) {
        this.mrrContent = mrrContent;
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
