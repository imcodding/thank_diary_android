package com.mia.thankdiary.src.login.service;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.util.SharedPreferenceUtil;
import com.mia.thankdiary.src.login.interfaces.LoginActivityView;
import com.mia.thankdiary.src.login.models.LoginResponse;

import org.jetbrains.annotations.NotNull;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.USER_ID;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class LoginService {
    private final LoginActivityView loginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    public void login(String userId, String hash) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                LoginResponse response = snapshot.getValue(LoginResponse.class);
                if(hash.equals(response.getHash())) {
                    USER_ID = userId;
                    SharedPreferenceUtil.putString("USER_ID", userId);
                    loginActivityView.loginSuccess(SUCCESS_CODE, response);
                } else {
                    // 로그인 실패 => 비밀번호 틀림
                    loginActivityView.loginSuccess(FAILURE_CODE, null);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                // 로그인 실패 => 아이디 없음
                loginActivityView.loginFailure(error.getMessage());
            }
        });
    }

}
