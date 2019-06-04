package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class GuestBook {
    private Long guestbookId;

    private String guestbookContent;

    private Long userId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String guestbookUrl;

    public Long getGuestbookId() {
        return guestbookId;
    }

    public void setGuestbookId(Long guestbookId) {
        this.guestbookId = guestbookId;
    }

    public String getGuestbookContent() {
        return guestbookContent;
    }

    public void setGuestbookContent(String guestbookContent) {
        this.guestbookContent = guestbookContent == null ? null : guestbookContent.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getGuestbookUrl() {
        return guestbookUrl;
    }

    public void setGuestbookUrl(String guestbookUrl) {
        this.guestbookUrl = guestbookUrl == null ? null : guestbookUrl.trim();
    }
}