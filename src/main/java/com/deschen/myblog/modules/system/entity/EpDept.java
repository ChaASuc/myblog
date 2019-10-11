package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class EpDept {
    private Long deptId;

    private String deptName;

    private Long deptParentId;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Long getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(Long deptParentId) {
        this.deptParentId = deptParentId;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}