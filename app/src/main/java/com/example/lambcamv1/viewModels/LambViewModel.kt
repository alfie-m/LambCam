package com.example.lambcamv1.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lambcamv1.data.LambDatabase
import com.example.lambcamv1.repositories.LambRepository
import com.example.lambcamv1.models.Lamb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LambViewModel(application: Application): AndroidViewModel(application) {

    // Lamb ViewModel to hold data needed for UI and notify UI of changes

    val readAllData: LiveData<List<Lamb>>
    val countLambs: LiveData<Int>
    val countSingles: LiveData<Int>
    val countTwins: LiveData<Int>
    val countTriplets: LiveData<Int>
    val countQuadruplets: LiveData<Int>
    val countEweLambs: LiveData<Int>
    val countTupLambs: LiveData<Int>
    private val repository: LambRepository

    init {
        val lambDao = LambDatabase.getDatabase(application).lambDao()
        repository = LambRepository(lambDao)

        readAllData = repository.readAllData
        countLambs = repository.countLambs
        countSingles = repository.countSingles
        countTwins = repository.countTwins
        countTriplets = repository.countTriplets
        countQuadruplets = repository.countQuadruplets
        countEweLambs = repository.countEweLambs
        countTupLambs = repository.countTupLambs
    }

    fun addLamb(lamb: Lamb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLamb(lamb)
        }
    }

    fun updateLamb(lamb: Lamb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLamb(lamb)
        }
    }

    fun deleteLamb(lamb: Lamb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLamb(lamb)
        }
    }

}