package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class Dir {
    private Long dirId;

    private String dirName;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String dirUrl;

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName == null ? null : dirName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDirUrl() {
        return dirUrl;
    }

    public void setDirUrl(String dirUrl) {
        this.dirUrl = dirUrl == null ? null : dirUrl.trim();
    }
}