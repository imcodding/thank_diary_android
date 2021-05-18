package com.mia.thankdiary.src.login.interfaces;

import com.mia.thankdiary.src.login.models.LoginResponse;

public interface LoginActivityView {
    void loginSuccess(int code, LoginResponse loginResponse);
    void loginFailure(String message);
}
