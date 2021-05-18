package com.mia.thankdiary.src.main.history.service;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.USER_ID;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class HistoryService {
    private final HistoryFragmentView historyFragmentView;

    public HistoryService(HistoryFragmentView historyFragmentView) {
        this.historyFragmentView = historyFragmentView;
    }

    public void getHistory(String date) {
        getDatabaseReference().child("diary").child(USER_ID).child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    historyFragmentView.getHistorySuccess(FAILURE_CODE, null);
                } else {
                    Diary diary = snapshot.getValue(Diary.class);
                    historyFragmentView.getHistorySuccess(SUCCESS_CODE, diary);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                historyFragmentView.getHistoryFailure(error.getMessage());
            }
        });
    }
}
