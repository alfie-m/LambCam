package com.example.lambcamv1.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lambcamv1.data.TupDatabase
import com.example.lambcamv1.repositories.TupRepository
import com.example.lambcamv1.models.Tup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TupViewModel(application: Application): AndroidViewModel(application) {

    // Tup ViewModel to hold data needed for UI and notify UI of changes

    val readAllData: LiveData<List<Tup>>
    val countTups: LiveData<Int>
    private val repository: TupRepository

    init {
        val tupDao = TupDatabase.getDatabase(application).tupDao()
        repository = TupRepository(tupDao)

        readAllData = repository.readAllData
        countTups = repository.countTups
    }

    fun addTup(tup: Tup){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTup(tup)
        }
    }

    fun updateTup(tup: Tup){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTup(tup)
        }
    }

    fun deleteTup(tup: Tup){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTup(tup)
        }
    }

    fun deleteAllTups(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTups()
        }
    }

}