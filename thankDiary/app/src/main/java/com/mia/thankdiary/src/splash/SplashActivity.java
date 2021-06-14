package com.mia.thankdiary.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivitySplashBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.common.util.SharedPreferenceUtil;
import com.mia.thankdiary.src.login.LoginActivity;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.main.MainActivity;
import com.mia.thankdiary.src.splash.interfaces.SplashActivityView;
import com.mia.thankdiary.src.splash.service.SplashService;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements SplashActivityView {

    private SplashService mSplashService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSplashService = new SplashService(this);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!SharedPreferenceUtil.getBoolean("AUTO_LOGIN")) {
                    requestLogin();
                    return;
                }
                String userId = SharedPreferenceUtil.getString("USER_ID");
                if(userId == null) requestLogin();
                else mSplashService.autoLogin(userId);
            }
        }, 2000);
    }

    private void requestLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void autoLoginSuccess(LoginResponse loginResponse) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", loginResponse);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void autoLoginFailure(String message) {
        showToast(getString(R.string.login_again_msg));
        requestLogin();
    }
}