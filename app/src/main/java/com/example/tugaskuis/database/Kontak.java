package com.example.tugaskuis.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kontak {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "no_hp")
    public String noHp;
}
