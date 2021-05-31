package com.mia.thankdiary.src.main.history.interfaces;

import com.mia.thankdiary.src.common.models.Diary;

import java.util.ArrayList;

public interface HistoryFragmentView {
    void getHistByDaySuccess(int code, ArrayList<Diary> list);
    void getHistByDayFailure(String message);

    void getHistAllSuccess(int code, ArrayList<Diary> list);
    void getHistAllFailure(String message);
}
