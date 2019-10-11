package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class EpUserRole {
    private Long uRId;

    private Long userId;

    private Long roleId;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Long getuRId() {
        return uRId;
    }

    public void setuRId(Long uRId) {
        this.uRId = uRId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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