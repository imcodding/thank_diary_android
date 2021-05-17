package com.mia.thankdiary.src.login.models;

import java.io.Serializable;
import java.util.HashMap;

public class LoginResponse implements Serializable {

    private String userId;
    private String hash;
    private String token;
    private int questionNo;

    public LoginResponse(String userId, String hash, int questionNo) {
        this.userId = userId;
        this.hash = hash;
        this.questionNo = questionNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("hash", hash);
        map.put("question", questionNo);

        return map;
    }
}

