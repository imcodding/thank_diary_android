package com.mia.thankdiary.src.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiaryDao {
    @Query("SELECT * FROM diary")
    public List<Diary> selectAllDiary();

    @Insert
    public void insertDiary(List<Diary> thanks);

}
