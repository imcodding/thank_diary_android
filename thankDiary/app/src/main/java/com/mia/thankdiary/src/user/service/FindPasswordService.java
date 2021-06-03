package com.mia.thankdiary.src.user.service;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.util.SharedPreferenceUtil;
import com.mia.thankdiary.src.login.models.LoginResponse;
import com.mia.thankdiary.src.user.interfaces.FindPasswordActivityView;

import org.jetbrains.annotations.NotNull;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class FindPasswordService {
    private final FindPasswordActivityView findPasswordActivityView;

    public FindPasswordService(FindPasswordActivityView findPasswordActivityView) {
        this.findPasswordActivityView = findPasswordActivityView;
    }

    public void checkId(String userId) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                LoginResponse response = snapshot.getValue(LoginResponse.class);
                if(response == null) {
                    findPasswordActivityView.checkIdSuccess(FAILURE_CODE, null);
                    return;
                }
                findPasswordActivityView.checkIdSuccess(SUCCESS_CODE, response);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                // 로그인 실패 => 아이디 없음
                findPasswordActivityView.checkIdFailure(error.getMessage());
            }
        });
    }

    public void updatePassword(String userId, String hash) {
        getDatabaseReference().child("users").child(userId).child("hash").setValue(hash)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        findPasswordActivityView.changePasswordSuccess(SUCCESS_CODE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        findPasswordActivityView.changePasswordFailure(e.getMessage());
                    }
                });
    }
}
