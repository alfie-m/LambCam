package com.example.lambcamv1.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lambcamv1.data.EweDatabase
import com.example.lambcamv1.repositories.EweRepository
import com.example.lambcamv1.models.Ewe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EweViewModel(application: Application): AndroidViewModel(application) {

    // Ewe ViewModel to hold data needed for UI and notify UI of changes

    val readAllData: LiveData<List<Ewe>>
    val countEwes: LiveData<Int>
    private val repository: EweRepository

    init {
        val eweDao = EweDatabase.getDatabase(application).eweDao()
        repository = EweRepository(eweDao)
        readAllData = repository.readAllData
        countEwes = repository.countEwes
    }

    fun addEwe(ewe: Ewe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEwe(ewe)
        }
    }

    fun updateEwe(ewe: Ewe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEwe(ewe)
        }
    }

    fun deleteEwe(ewe: Ewe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEwe(ewe)
        }
    }

    fun deleteAllEwes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEwes()
        }
    }

}