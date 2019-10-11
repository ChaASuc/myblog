package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class EpPermission {
    private Long permId;

    private String permDescription;

    private Boolean reqMethod;

    private String zuulPrefix;

    private String serverMethod;

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

    public Boolean getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(Boolean reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix == null ? null : zuulPrefix.trim();
    }

    public String getServerMethod() {
        return serverMethod;
    }

    public void setServerMethod(String serverMethod) {
        this.serverMethod = serverMethod == null ? null : serverMethod.trim();
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