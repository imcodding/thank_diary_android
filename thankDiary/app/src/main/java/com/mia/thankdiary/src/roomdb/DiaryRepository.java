package com.mia.thankdiary.src.roomdb;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import static com.mia.thankdiary.config.ApplicationClass.roomDB;

public class DiaryRepository {

    private DiaryDao diaryDao = roomDB.diaryDao();

//    Completable insertDiary(Diary diary) { return diaryDao.insertDiary(diary); }
}
