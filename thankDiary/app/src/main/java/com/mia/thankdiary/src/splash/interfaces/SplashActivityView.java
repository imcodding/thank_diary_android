package com.mia.thankdiary.src.splash.interfaces;

import com.mia.thankdiary.src.login.models.LoginResponse;

public interface SplashActivityView {
    void autoLoginSuccess(LoginResponse loginResponse);
    void autoLoginFailure(String message);
}
