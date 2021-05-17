package com.mia.thankdiary.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivitySplashBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.login.LoginActivity;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.main.MainActivity;
import com.mia.thankdiary.src.splash.interfaces.SplashActivityView;
import com.mia.thankdiary.src.splash.service.SplashService;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements SplashActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SplashService mSplashService = new SplashService(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 2000);
    }

    private void requestLogin() {

    }

    @Override
    public void autoLoginSuccess(LoginResponse loginResponse) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
        finish();
    }

    @Override
    public void autoLoginFailure(String message) {

    }
}