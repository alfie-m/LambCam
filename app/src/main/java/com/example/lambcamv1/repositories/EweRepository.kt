package com.example.lambcamv1.repositories

import androidx.lifecycle.LiveData
import com.example.lambcamv1.data.EweDao
import com.example.lambcamv1.models.Ewe

class EweRepository(private val eweDao: EweDao) {

    // Ewe repository for UI and database communication

    // Pass readAllData method from Dao
    val readAllData: LiveData<List<Ewe>> = eweDao.readAllEweData()

    // Pass countEwes method from Dao
    val countEwes: LiveData<Int> = eweDao.countEwes()

    suspend fun addEwe(ewe: Ewe){
        eweDao.addEwe(ewe)
    }

    suspend fun updateEwe(ewe: Ewe){
        eweDao.updateEwe(ewe)
    }

    suspend fun deleteEwe(ewe: Ewe){
        eweDao.deleteEwe(ewe)
    }

    suspend fun deleteAllEwes(){
        eweDao.deleteAllEwes()
    }

}