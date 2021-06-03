package com.mia.thankdiary.src.user.interfaces;

import com.mia.thankdiary.src.login.models.LoginResponse;

public interface FindPasswordActivityView {
    void checkIdSuccess(int code, LoginResponse response);
    void checkIdFailure(String message);

    void changePasswordSuccess(int code);
    void changePasswordFailure(String message);
}
