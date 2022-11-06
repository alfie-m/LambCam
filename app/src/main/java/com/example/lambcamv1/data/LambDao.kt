package com.example.lambcamv1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lambcamv1.models.Lamb

@Dao
interface LambDao {

    // Lamb Data Access Object interface to mediate between app and lamb database

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLamb(lamb: Lamb)

    @Update
    suspend fun updateLamb(lamb: Lamb)

    @Delete
    suspend fun deleteLamb(lamb: Lamb)

    // Query to get all data in lamb database to be used in recyclerView
    @Query("SELECT * FROM lamb_table ORDER BY id ASC")
    fun readAllLambData(): LiveData<List<Lamb>>

    // Query to count number of lamb in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table")
    fun countLambs(): LiveData<Int>

    // Query to count number of singles in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE siblings = 'Single'")
    fun countSingles(): LiveData<Int>

    // Query to count number of twins in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE siblings = 'Twin'")
    fun countTwins(): LiveData<Int>

    // Query to count number of triplets in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE siblings = 'Triplet'")
    fun countTriplets(): LiveData<Int>

    // Query to count number of quadruplets in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE siblings = 'Quadruplet'")
    fun countQuadruplets(): LiveData<Int>

    // Query to count number of ewe lambs in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE sex = 'Ewe'")
    fun countEweLambs(): LiveData<Int>

    // Query to count number of tup lambs in lamb database for statistics
    @Query("SELECT COUNT(*) FROM lamb_table WHERE sex = 'Tup'")
    fun countTupLambs(): LiveData<Int>

}