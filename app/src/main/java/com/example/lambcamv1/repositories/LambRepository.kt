package com.example.lambcamv1.repositories

import androidx.lifecycle.LiveData
import com.example.lambcamv1.data.LambDao
import com.example.lambcamv1.models.Lamb

class LambRepository(private val lambDao: LambDao) {

    // Lamb repository for UI and database communication

    // Pass methods from Dao
    val readAllData: LiveData<List<Lamb>> = lambDao.readAllLambData()
    val countLambs: LiveData<Int> = lambDao.countLambs()
    val countSingles: LiveData<Int> = lambDao.countSingles()
    val countTwins: LiveData<Int> = lambDao.countTwins()
    val countTriplets: LiveData<Int> = lambDao.countTriplets()
    val countQuadruplets: LiveData<Int> = lambDao.countQuadruplets()
    val countEweLambs: LiveData<Int> = lambDao.countEweLambs()
    val countTupLambs: LiveData<Int> = lambDao.countTupLambs()

    suspend fun addLamb(lamb: Lamb){
        lambDao.addLamb(lamb)
    }

    suspend fun updateLamb(lamb: Lamb){
        lambDao.updateLamb(lamb)
    }

    suspend fun deleteLamb(lamb: Lamb){
        lambDao.deleteLamb(lamb)
    }

}