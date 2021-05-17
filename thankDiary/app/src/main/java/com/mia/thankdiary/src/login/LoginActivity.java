package com.mia.thankdiary.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mia.thankdiary.databinding.ActivityLoginBinding;
import com.mia.thankdiary.src.common.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}