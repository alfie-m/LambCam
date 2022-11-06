package com.example.lambcamv1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lambcamv1.models.Ewe

@Dao
interface EweDao {

    // Ewe Data Access Object interface to mediate between app and ewe database

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEwe(ewe: Ewe)

    @Update
    suspend fun updateEwe(ewe: Ewe)

    @Delete
    suspend fun deleteEwe(ewe: Ewe)

    // Query to delete all ewes in ewe database
    @Query("DELETE FROM ewe_table")
    suspend fun deleteAllEwes()

    // Query to get all data in ewe database to be used in recyclerView
    @Query("SELECT * FROM ewe_table ORDER BY id ASC")
    fun readAllEweData(): LiveData<List<Ewe>>

    // Query to count number of ewes in ewe database for statistics
    @Query("SELECT COUNT(*) FROM ewe_table")
    fun countEwes(): LiveData<Int>
}