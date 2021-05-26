package com.mia.thankdiary.src.main.history.service;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.common.util.DateFormatUtil;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;

import java.util.ArrayList;
import java.util.List;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.USER_ID;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class HistoryService {
    private final HistoryFragmentView historyFragmentView;

    public HistoryService(HistoryFragmentView historyFragmentView) {
        this.historyFragmentView = historyFragmentView;
    }

    public void getHistoryByDay(String date) {
        getDatabaseReference().child("diary").child(USER_ID)
                .child(DateFormatUtil.getYear(date)).child(DateFormatUtil.getMonth(date)).child(DateFormatUtil.getDay(date))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue() == null) {
                            historyFragmentView.getHistByMonthSuccess(FAILURE_CODE, null);
                        } else {
                            Iterable<DataSnapshot> data = snapshot.getChildren();
                            ArrayList<Diary> list = new ArrayList<>();

                            for(DataSnapshot dataSnapshot : data) {
                                Diary diary = dataSnapshot.getValue(Diary.class);
                                list.add(diary);
                            }

                            historyFragmentView.getHistByMonthSuccess(SUCCESS_CODE, list);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        historyFragmentView.getHistByMonthFailure(error.getMessage());
                    }
                });
    }

    public void getHistoryByMonth(String date) {
        getDatabaseReference().child("diary").child(USER_ID)
                .child(DateFormatUtil.getYear(date)).child(DateFormatUtil.getMonth(date))
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    historyFragmentView.getHistByMonthSuccess(FAILURE_CODE, null);
                } else {
                    Iterable<DataSnapshot> data = snapshot.getChildren();
                    ArrayList<Diary> list = new ArrayList<>();

                    for(DataSnapshot dataSnapshot : data) {
                        Diary diary = dataSnapshot.getValue(Diary.class);
                        list.add(diary);
                    }

                    historyFragmentView.getHistByMonthSuccess(SUCCESS_CODE, list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                historyFragmentView.getHistByMonthFailure(error.getMessage());
            }
        });
    }

    public void getHistoryByYear(String date) {
        getDatabaseReference().child("diary").child(USER_ID).child(DateFormatUtil.getYear(date))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue() == null) {
                            historyFragmentView.getHistByYearSuccess(FAILURE_CODE, null);
                        } else {
                            Iterable<DataSnapshot> a = snapshot.getChildren();
                            ArrayList<Diary> list = new ArrayList<>();

                            for(DataSnapshot month : a) {
                                Iterable<DataSnapshot> b = month.getChildren();
                                for(DataSnapshot day : b) {
                                    Diary diary = day.getValue(Diary.class);
                                    list.add(diary);
                                }
                            }

                            historyFragmentView.getHistByYearSuccess(SUCCESS_CODE, list);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        historyFragmentView.getHistByYearFailure(error.getMessage());
                    }
                });
    }
}
