package com.ono.locatporium;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DtoMemory.class, DtoActivity.class}, version = 1)
public abstract class DatabaseClass extends RoomDatabase {
    public abstract MemoriesDao memoriesDao();

    public abstract ActivityDao activityDao();
}
