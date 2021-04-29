package com.mia.thankdiary.src.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class Diary {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "seq_no")
    private int seqNo;
    @ColumnInfo(name = "first_thank")
    private String firstThank;
    @ColumnInfo(name = "second_thank")
    private String secondThank;
    @ColumnInfo(name = "third_thank")
    private String thirdThank;
    @ColumnInfo(name = "crt_date")
    private String crtDate;
    @ColumnInfo(name = "upd_date")
    private String updDate;
    @ColumnInfo(name = "del_date")
    private String delDate;

    public Diary(String firstThank, String secondThank, String thirdThank, String crtDate) {
        this.firstThank = firstThank;
        this.secondThank = secondThank;
        this.thirdThank = thirdThank;
        this.crtDate = crtDate;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getFirstThank() {
        return firstThank;
    }

    public void setFirstThank(String firstThank) {
        this.firstThank = firstThank;
    }

    public String getSecondThank() {
        return secondThank;
    }

    public void setSecondThank(String secondThank) {
        this.secondThank = secondThank;
    }

    public String getThirdThank() {
        return thirdThank;
    }

    public void setThirdThank(String thirdThank) {
        this.thirdThank = thirdThank;
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
