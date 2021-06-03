package com.mia.thankdiary.src.user;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivityFindPasswordBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.common.util.HashUtil;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.user.interfaces.FindPasswordActivityView;
import com.mia.thankdiary.src.user.service.FindPasswordService;

import java.security.NoSuchAlgorithmException;

import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;

public class FindPasswordActivity extends BaseActivity<ActivityFindPasswordBinding> implements FindPasswordActivityView {

    private FindPasswordService mFindPasswordService;
    private boolean isCheckedId;
    private boolean isCheckedQuestion;
    private String mQuestionAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
        initView();
        initListener();
    }

    private void initVariable() {
        mFindPasswordService = new FindPasswordService(this);
    }

    private void initView() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sign_up_questions,
                R.layout.item_question
        );
        binding.findPwSpinnerQuestion.setAdapter(arrayAdapter);
    }

    private void initListener() {
        binding.findPwEtId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    checkId();
                    return true;
                }
                return false;
            }
        });

        binding.findPwEtQuestionAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    checkQuestion();
                    return true;
                }
                return false;
            }
        });

        binding.findPwEtNewCheck.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    changePassword();
                    return true;
                }
                return false;
            }
        });
    }

    private void checkId() {
        if(!validate()) return;
        showProgressDialog();
        mFindPasswordService.checkId(String.valueOf(binding.findPwEtId.getText()));
    }

    private void checkQuestion() {
        if(!validate()) return;
        String inputAnswer = String.valueOf(binding.findPwEtQuestionAnswer.getText());
        if(inputAnswer.equals(mQuestionAnswer)) {
            binding.findPwEtNew.setVisibility(View.VISIBLE);
            binding.findPwEtNewCheck.setVisibility(View.VISIBLE);
            binding.findPwEtQuestionAnswer.setEnabled(false);
            isCheckedQuestion = true;
        } else {
            showToast(getString(R.string.find_pw_question_check));
            isCheckedQuestion = false;
        }
    }

    private void changePassword() {
        if(!validate()) return;
        showProgressDialog();

        try {
            String userId = String.valueOf(binding.findPwEtId.getText());
            String hash = HashUtil.sha256(String.valueOf(binding.findPwEtNew.getText()));
            mFindPasswordService.updatePassword(userId, hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkIdSuccess(int code, LoginResponse response) {
        hideProgressDialog();
        if(code == SUCCESS_CODE) {
            binding.findPwEtId.setEnabled(false);
            binding.findPwSpinnerQuestion.setVisibility(View.VISIBLE);
            binding.findPwEtQuestionAnswer.setVisibility(View.VISIBLE);

            isCheckedId = true;
            binding.findPwSpinnerQuestion.setSelection(response.getQuestionNo());
            binding.findPwSpinnerQuestion.setEnabled(false);

            mQuestionAnswer = response.getQuestionAnswer();
        } else {
            showToast(getString(R.string.login_id_error));
            isCheckedId = false;
        }
    }

    @Override
    public void checkIdFailure(String message) {
        hideProgressDialog();
        showToast(getString(R.string.network_not_working));
    }

    @Override
    public void changePasswordSuccess(int code) {
        hideProgressDialog();
        showToast(getString(R.string.change_pw_success));
        finish();
    }

    @Override
    public void changePasswordFailure(String message) {
        hideProgressDialog();
        showToast(getString(R.string.change_pw_failure));
    }

    private boolean validate() {
        String value = String.valueOf(binding.findPwEtId.getText());
        if(value.isEmpty()) {
            showToast(getString(R.string.sign_up_id_hint));
            return false;
        }

        if(isCheckedId) {
            value = String.valueOf(binding.findPwEtQuestionAnswer.getText());
            if(value.isEmpty()) {
                showToast(getString(R.string.sign_up_question_hint));
                return false;
            }
        }

        if(isCheckedQuestion) {
            value = String.valueOf(binding.findPwEtNew.getText());
            if(value.isEmpty()) {
                showToast(getString(R.string.find_pw_new_hint));
                return false;
            }
            value = String.valueOf(binding.findPwEtNewCheck.getText());
            if(value.isEmpty()) {
                showToast(getString(R.string.find_pw_new_check_hint));
                return false;
            }
        }

        return true;
    }
}