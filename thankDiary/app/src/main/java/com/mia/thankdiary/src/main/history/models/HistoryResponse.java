package com.mia.thankdiary.src.main.history.models;

public class HistoryResponse {
    private int seqNo;
    private String content;
    private String userId;
    private String crtDate;
    private String updDate;
    private String delDate;

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public String getUpdDate() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }
}
