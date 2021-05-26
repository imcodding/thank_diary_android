package com.mia.thankdiary.src.main.write.service;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.common.util.DateFormatUtil;
import com.mia.thankdiary.src.main.write.interfaces.WriteFragmentView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.USER_ID;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class WriteService {
    private final WriteFragmentView writeFragmentView;

    public WriteService(WriteFragmentView writeFragmentView) {
        this.writeFragmentView = writeFragmentView;
    }

    public void writeDiary(HashMap<String, Object> postValues, Diary diary) {
        getDatabaseReference().child("diary").child(USER_ID)
                .child(diary.getYear()).child(diary.getMonth()).child(diary.getDay())
                .setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        writeFragmentView.writeSuccess(SUCCESS_CODE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        writeFragmentView.writeFailure(e.getMessage());
                    }
                });
    }

    public void getDiaryToday(String crtDate) {
        getDatabaseReference().child("diary").child(USER_ID)
                .child(DateFormatUtil.getYear(crtDate)).child(DateFormatUtil.getMonth(crtDate)).child(DateFormatUtil.getDay(crtDate))
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    writeFragmentView.getDiarySuccess(FAILURE_CODE, null);
                } else {
                    Diary diary = snapshot.getValue(Diary.class);
                    writeFragmentView.getDiarySuccess(SUCCESS_CODE, diary);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                writeFragmentView.getDiaryFailure(error.getMessage());
            }
        });
    }
}
