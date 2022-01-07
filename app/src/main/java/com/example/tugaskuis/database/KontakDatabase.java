package com.example.tugaskuis.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Kontak.class}, version = 1)
public abstract class KontakDatabase extends RoomDatabase {
    public abstract KontakDao kontakDao();
}
