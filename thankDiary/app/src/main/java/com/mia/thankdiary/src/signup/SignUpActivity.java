package com.mia.thankdiary.src.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivitySignUpBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.common.util.HashUtil;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.signup.service.SignUpService;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> implements SignUpActivityView{

    private int mQuestionNo;
    private SignUpService mSignUpService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
        initView();
        initListener();
    }

    private void initVariable() {
        mQuestionNo = 0;
        mSignUpService = new SignUpService(this);
    }

    private void initView() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sign_up_questions,
                R.layout.item_question
        );
        binding.signUpSpinnerQuestion.setAdapter(arrayAdapter);
        binding.signUpEtId.setPrivateImeOptions("defaultInputMode=english;");
    }

    private void initListener() {

        binding.signUpSpinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mQuestionNo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.signUpEtQuestionAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    signUp();
                    return true;
                }
                return false;
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
        showProgressDialog();
        mSignUpService.checkDuplicateId(userId);
    }

    private boolean validate() {
        String value = String.valueOf(binding.signUpEtId.getText());
        if(value.isEmpty()) {
            showToast(getString(R.string.sign_up_id_hint));
            return false;
        }

        if(!checkEngNum(value.toLowerCase())) {
            showToast(getString(R.string.sign_up_id_pattern));
            return false;
        }

        String value2 = String.valueOf(binding.signUpEtPassword.getText());
        if(value2.isEmpty()) {
            showToast(getString(R.string.sign_up_pw_hint));
            return false;
        }

        if(!checkEngNum(value2.toLowerCase())) {
            showToast(getString(R.string.sign_up_pw_pattern));
            return false;
        }

        if(value.equals(value2)) {
            showToast(getString(R.string.sign_up_pw_match_id));
            return false;
        }

        value = String.valueOf(binding.signUpEtQuestionAnswer.getText());
        if(value.isEmpty()) {
            showToast(getString(R.string.sign_up_question_hint));
            return false;
        }

        return true;
    }

    @Override
    public void signUpSuccess(int code) {
        hideProgressDialog();
        showToast(getString(R.string.sign_up_success));
        finish();
    }

    @Override
    public void signUpFailure(String message) {
        hideProgressDialog();
        showToast(getString(R.string.sign_up_failure));
    }

    @Override
    public void checkIdSuccess(int code) {
        hideProgressDialog();
        if(code == SUCCESS_CODE) { // 중복 아이디가 없다는 의미
            String userId = String.valueOf(binding.signUpEtId.getText());
            String password = String.valueOf(binding.signUpEtPassword.getText());
            String questionAnswer = String.valueOf(binding.signUpEtQuestionAnswer.getText());
            String hash = "";
            try {
                hash = HashUtil.sha256(password.toLowerCase());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            LoginResponse model = new LoginResponse(userId, hash, mQuestionNo, questionAnswer);
            HashMap<String, Object> postValues = model.toMap();

            mSignUpService.signUp(userId, postValues);
        } else {
            showToast(getString(R.string.sign_up_id_check));
        }

    }

    @Override
    public void checkIdFailure(String message) {
        showToast(getString(R.string.network_not_working));
    }
}