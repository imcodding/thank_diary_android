package com.mia.thankdiary.src.main.write.interfaces;

import com.mia.thankdiary.src.common.models.Diary;

public interface WriteFragmentView {
    void writeSuccess(int code);
    void writeFailure(String message);

    void getDiarySuccess(int code, Diary diary);
    void getDiaryFailure(String message);

}
