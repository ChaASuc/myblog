package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class EpRolePermission {
    private Long rPId;

    private Long roleId;

    private Long permId;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Long getrPId() {
        return rPId;
    }

    public void setrPId(Long rPId) {
        this.rPId = rPId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
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