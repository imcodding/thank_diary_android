package com.mia.thankdiary.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivityLoginBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.login.interfaces.LoginActivityView;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.login.service.LoginService;
import com.mia.thankdiary.src.main.MainActivity;
import com.mia.thankdiary.src.signup.SignUpActivity;
import com.mia.thankdiary.src.user.FindPasswordActivity;
import com.mia.thankdiary.util.HashUtil;

import java.security.NoSuchAlgorithmException;

import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginActivityView {

    LoginService mLoginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
        initListener();
    }

    private void initVariable() {
        mLoginService = new LoginService(this);
    }

    private void initListener() {
        // 회원가입
        binding.loginTvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
        // 비밀번호 찾기
        binding.loginTvFindPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, FindPasswordActivity.class);
            startActivity(intent);
        });
        // 로그인 버튼 클릭
        binding.loginTvBtn.setOnClickListener(v -> {
            login();
        });
        // 로그인 엔터 클릭
        binding.loginEtPw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    private void login() {
        if(!validate()) return;

        String userId = String.valueOf(binding.loginEtId.getText());
        String password = String.valueOf(binding.loginEtPw.getText());

        try {
            showProgressDialog();
            mLoginService.login(userId, HashUtil.sha256(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginSuccess(int code, LoginResponse user) {
        hideProgressDialog();
        if(code == SUCCESS_CODE) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            showToast(getString(R.string.login_password_error));
        }
    }

    @Override
    public void loginFailure(String message) {
        hideProgressDialog();
        showToast(getString(R.string.login_id_error));
    }

    private boolean validate() {
        String value = String.valueOf(binding.loginEtId.getText());
        if (value.isEmpty()) {
            showToast(getString(R.string.sign_up_id_hint));
            return false;
        }

        value = String.valueOf(binding.loginEtPw.getText());
        if (value.isEmpty()) {
            showToast(getString(R.string.sign_up_pw_hint));
            return false;
        }

        return true;
    }
}