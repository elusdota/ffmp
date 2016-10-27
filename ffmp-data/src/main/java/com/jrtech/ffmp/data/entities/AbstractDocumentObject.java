package com.jrtech.ffmp.data.entities;

/**
 * Created by jiangliang on 2016/10/27.
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractDocumentObject extends AbstractDomainObject {
    //附件名称
    private String name;
    //新建文件名
    private String newFileName;
    //附件类型 pdf/word 等
    private String contentType;
    //缩略图名称
    private String thumbnailFilename;
    //文件大小
    @Column(name = "size_")
    private Long size;
    //缩略图大小
    private Long thumbnailSize;
    @Transient
    private String url;
    @Transient
    private String thumbnailUrl;
    @Transient
    private String deleteUrl;
    @Transient
    private String deleteType;
    //下载地址
    @Transient
    private String downloadUrl;
    //上传时间
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date dateCreated;
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
