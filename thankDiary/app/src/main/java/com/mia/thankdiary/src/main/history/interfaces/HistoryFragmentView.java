package com.mia.thankdiary.src.main.history.interfaces;

import com.mia.thankdiary.src.common.models.Diary;

import java.util.ArrayList;

public interface HistoryFragmentView {
    void getHistByDaySuccess(int code, Diary diary);
    void getHistByDayFailure(String message);

    void getHistByYearSuccess(int code, ArrayList<Diary> list);
    void getHistByYearFailure(String message);

    void getHistByMonthSuccess(int code, ArrayList<Diary> list);
    void getHistByMonthFailure(String message);

}
