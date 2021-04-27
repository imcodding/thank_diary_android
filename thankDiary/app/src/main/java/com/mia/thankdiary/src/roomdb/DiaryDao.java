package com.mia.thankdiary.src.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiaryDao {
    @Query("SELECT * FROM Diary")
    LiveData<List<Diary>> getAll();
}
