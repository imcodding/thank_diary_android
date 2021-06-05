package com.mia.thankdiary.src.main.history.service;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.common.util.DateFormatUtil;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;

import java.util.ArrayList;

import static com.mia.thankdiary.config.ApplicationClass.FAILURE_CODE;
import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.USER_ID;
import static com.mia.thankdiary.config.ApplicationClass.getDatabaseReference;

public class HistoryService {
    private final HistoryFragmentView historyFragmentView;

    public HistoryService(HistoryFragmentView historyFragmentView) {
        this.historyFragmentView = historyFragmentView;
    }

//    public void getHistoryByDay(String year, String month, String day) {
//        getDatabaseReference().child("diary").child(USER_ID)
//                .child(year).child(month).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.getValue() == null) {
//                            historyFragmentView.getHistByDaySuccess(FAILURE_CODE, null);
//                        } else {
//                            Iterable<DataSnapshot> data = snapshot.getChildren();
//                            ArrayList<Diary> list = new ArrayList<>();
//
//                            for(DataSnapshot dataSnapshot : data) {
//                                Diary diary = snapshot.getValue(Diary.class);
//                                list.add(diary);
//                            }
//                            historyFragmentView.getHistByDaySuccess(SUCCESS_CODE, diary);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        historyFragmentView.getHistByDayFailure(error.getMessage());
//                    }
//                });
//    }

    public void getHistoryByDay(String year, String month) {
        getDatabaseReference().child("diary").child(USER_ID).child(year).child(month)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    historyFragmentView.getHistByDaySuccess(FAILURE_CODE, null);
                } else {
                    Iterable<DataSnapshot> data = snapshot.getChildren();
                    ArrayList<Diary> list = new ArrayList<>();

                    for(DataSnapshot dataSnapshot : data) {
                        Diary diary = dataSnapshot.getValue(Diary.class);
                        list.add(diary);
                    }

                    historyFragmentView.getHistByDaySuccess(SUCCESS_CODE, list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                historyFragmentView.getHistByDayFailure(error.getMessage());
            }
        });
    }

    public void getHistoryAll(String date) {
        getDatabaseReference().child("diary").child(USER_ID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue() == null) {
                            historyFragmentView.getHistAllSuccess(FAILURE_CODE, null);
                        } else {
                            Iterable<DataSnapshot> a = snapshot.getChildren();
                            ArrayList<Diary> list = new ArrayList<>();

                            for(DataSnapshot month : a) {
                                Iterable<DataSnapshot> b = month.getChildren();
                                for(DataSnapshot day : b) {
                                    Iterable<DataSnapshot> c = day.getChildren();
                                    for(DataSnapshot data : c) {
                                        Diary diary = data.getValue(Diary.class);
                                        list.add(diary);
                                    }
                                }
                            }

                            historyFragmentView.getHistAllSuccess(SUCCESS_CODE, list);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        historyFragmentView.getHistAllFailure(error.getMessage());
                    }
                });
    }
}
