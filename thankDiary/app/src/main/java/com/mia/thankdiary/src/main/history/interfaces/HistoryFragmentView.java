package com.mia.thankdiary.src.main.history.interfaces;

import com.mia.thankdiary.src.common.models.Diary;

public interface HistoryFragmentView {
    void getHistorySuccess(int code, Diary diary);
    void getHistoryFailure(String message);

}
