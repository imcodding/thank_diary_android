package com.mia.thankdiary.src.common.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD;
import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD_TIME;

public class Diary {
    private String year;
    private String month;
    private String day;
    private String crtDate;
    private String updDate;
    private String delDate;
    private String crtDateTime;
    private ArrayList<String> contents;

    public Diary() {}
    public Diary(ArrayList<String> contents) {
        this.crtDate = YYYY_MM_DD.format(new Date());
        this.crtDateTime = YYYY_MM_DD_TIME.format(new Date());
        this.year = crtDate.split("-")[0];
        this.month = crtDate.split("-")[1];
        this.day = crtDate.split("-")[2];
        this.contents = contents;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getCrtDateTime() {
        return crtDateTime;
    }

    public void setCrtDateTime(String crtDateTime) {
        this.crtDateTime = crtDateTime;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("crtDate", crtDate);
        map.put("updDate", updDate);
        map.put("delDate", delDate);
        map.put("crtDateTime", crtDateTime);
        map.put("contents", contents);
        return map;
    }
}
