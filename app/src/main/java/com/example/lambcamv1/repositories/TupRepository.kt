package com.example.lambcamv1.repositories

import androidx.lifecycle.LiveData
import com.example.lambcamv1.data.TupDao
import com.example.lambcamv1.models.Tup

class TupRepository(private val tupDao: TupDao) {

    // Tup repository for UI and database communication

    // Pass readAllData method from Dao
    val readAllData: LiveData<List<Tup>> = tupDao.readAllData()

    // Pass countTups method from Dao
    val countTups: LiveData<Int> = tupDao.countTups()

    suspend fun addTup(tup: Tup){
        tupDao.addTup(tup)
    }

    suspend fun updateTup(tup: Tup){
        tupDao.updateTup(tup)
    }

    suspend fun deleteTup(tup: Tup){
        tupDao.deleteTup(tup)
    }

    suspend fun deleteAllTups(){
        tupDao.deleteAllTups()
    }
}