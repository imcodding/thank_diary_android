package com.mia.thankdiary.src.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivitySignUpBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.common.util.HashUtil;
import com.mia.thankdiary.src.login.models.LoginResponse;

import java.security.NoSuchAlgorithmException;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {

    private int questionNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initListener();
    }

    private void initView() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sign_up_questions,
                R.layout.item_question
        );
        binding.signUpSpinnerQuestion.setAdapter(arrayAdapter);
    }

    private void initListener() {

        binding.signUpSpinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                questionNo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.signUpTvComplete.setOnClickListener(v->{
            signUp();
        });

        binding.signUpCancel.setOnClickListener(v->{
            finish();
        });
    }

    private void signUp() {
        if(!validate()) return;

        String userId = String.valueOf(binding.signUpEtId.getText());
        String password = String.valueOf(binding.signUpEtPassword.getText());
        String hash = "";
        try {
            hash = HashUtil.sha256(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        LoginResponse model = new LoginResponse(userId, hash, questionNo);

    }

    private boolean validate() {
        String value = String.valueOf(binding.signUpEtId.getText());
        if(value.isEmpty()) {
            showToast(getString(R.string.sign_up_id_hint));
            return false;
        }

        value = String.valueOf(binding.signUpEtPassword.getText());
        if(value.isEmpty()) {
            showToast(getString(R.string.sign_up_pw_hint));
            return false;
        }

        if(!checkDuplicateId()) {
            showToast(getString(R.string.sign_up_id_check));
            return false;
        }

        return true;
    }

    private boolean checkDuplicateId() {

        return true;
    }
}