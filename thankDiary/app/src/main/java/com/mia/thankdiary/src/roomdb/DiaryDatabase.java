package com.mia.thankdiary.src.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Diary.class}, version = 1)
public abstract class DiaryDatabase extends RoomDatabase {
    // 싱글톤 (리소스 적게 사용하여 메모리 낭비 줄이기 위해서)
    private static DiaryDatabase instance;

    public abstract DiaryDao diaryDao();

    //DB 객체 생성 가져오기
    public static DiaryDatabase getDatabase(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, DiaryDatabase.class, "diary-db").build();
        }
        return instance;
    }

    //DB 객체 제거
    public static void destroyInstance() {
        instance = null;
    }
}
