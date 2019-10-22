package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class EpPermission {
    private Long permId;

    private String permDescription;

    private Integer reqMethod;

    private String zuulPrefix;

    private String serverPrefix;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public String getPermDescription() {
        return permDescription;
    }

    public void setPermDescription(String permDescription) {
        this.permDescription = permDescription == null ? null : permDescription.trim();
    }

    public Integer getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(Integer reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix == null ? null : zuulPrefix.trim();
    }

    public String getServerPrefix() {
        return serverPrefix;
    }

    public void setServerPrefix(String serverPrefix) {
        this.serverPrefix = serverPrefix == null ? null : serverPrefix.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
}