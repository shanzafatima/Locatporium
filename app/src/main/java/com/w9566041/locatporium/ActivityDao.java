package com.w9566041.locatporium;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao {
    @Query("select * from ACTIVITY")
    List<DtoActivity> getAllActivities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewActivity(DtoActivity activity);

    @Delete
    void deleteActivity(DtoActivity activity);

    @Query("delete from ACTIVITY")
    void deleteAllActivities();
}
