package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * app自动更新
 * Created by suelmer on 2016/10/20.
 */
@Entity
@Table(name = "AutoUpdate")
public class AutoUpdate extends AbstractNamedObject {

    //最新版本名称
    private String versionName;
    //最新版本号
    private String versionCode;
    //更新内容
    private String versionUpdateContent;
    //下载地址
    private String versionUpdateAddress;

    //创建时间
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionUpdateAddress() {
        return versionUpdateAddress;
    }

    public void setVersionUpdateAddress(String versionUpdateAddress) {
        this.versionUpdateAddress = versionUpdateAddress;
    }

    public String getVersionUpdateContent() {
        return versionUpdateContent;
    }

    public void setVersionUpdateContent(String versionUpdateContent) {
        this.versionUpdateContent = versionUpdateContent;
    }
}
