package com.example.lambcamv1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lambcamv1.models.Tup

@Dao
interface TupDao {

    // Tup Data Access Object interface to mediate between app and tup database

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTup(tup: Tup)

    @Update
    suspend fun updateTup(tup: Tup)

    @Delete
    suspend fun deleteTup(tup: Tup)

    // Query to delete all tups in tup database
    @Query("DELETE FROM tup_table")
    suspend fun deleteAllTups()

    // Query to get all data in tup database to be used in recyclerView
    @Query("SELECT * FROM tup_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Tup>>

    // Query to count number of tups in tup database for statistics
    @Query("SELECT COUNT(*) FROM tup_table")
    fun countTups(): LiveData<Int>
}