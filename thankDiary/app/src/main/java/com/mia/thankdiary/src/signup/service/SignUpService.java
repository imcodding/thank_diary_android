package com.mia.thankdiary.src.signup.service;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.signup.SignUpActivityView;

import java.util.HashMap;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class SignUpService {
    private final SignUpActivityView signUpActivityView;

    public SignUpService(SignUpActivityView signUpActivityView) {
        this.signUpActivityView = signUpActivityView;
    }

    public void checkDuplicateId(String userId) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null) {
                    signUpActivityView.checkIdSuccess(FAILURE_CODE);
                    return;
                }
                signUpActivityView.checkIdSuccess(SUCCESS_CODE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                signUpActivityView.checkIdFailure(error.getMessage());
            }
        });
    }

    public void signUp(String userId, HashMap<String, Object> postValues) {
        getDatabaseReference().child("users").child(userId).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        signUpActivityView.signUpSuccess(SUCCESS_CODE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        signUpActivityView.signUpFailure(e.getMessage());
                    }
                });
    }
}
