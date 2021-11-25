package com.yhjx.ministryhealth.bean;

import com.library.basemodule.entity.BaseEntity;

public class UpdateAppBean extends BaseEntity {

    /**
     * id : 1
     * version : 2
     * downloadLink : null
     * platfrom : 1
     * isForcedUpdating : 0
     * updateTitle : 版本升级
     * updateContent : 优化
     */

    private int id;
    private String version;
    private String downloadLink;
    private String platfrom;
    private String isForcedUpdating;
    private String updateTitle;
    private String updateContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public String getIsForcedUpdating() {
        return isForcedUpdating;
    }

    public void setIsForcedUpdating(String isForcedUpdating) {
        this.isForcedUpdating = isForcedUpdating;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
