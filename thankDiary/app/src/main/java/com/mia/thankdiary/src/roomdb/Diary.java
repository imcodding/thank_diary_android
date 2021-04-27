package com.mia.thankdiary.src.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diary {
    @PrimaryKey(autoGenerate = true)
    private int seqNo;
    
}
