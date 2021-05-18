package com.mia.thankdiary.src.signup;

public interface SignUpActivityView {
     void signUpSuccess(int code);
     void signUpFailure(String message);

     void checkIdSuccess(int code);
     void checkIdFailure(String message);
}
