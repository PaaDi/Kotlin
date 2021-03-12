package com.madera.kotlin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.madera.kotlin.Entity.Chantier
import com.madera.kotlin.Repository.ChantierRepository
import java.lang.IllegalArgumentException

class ChantierViewModel(private val repository: ChantierRepository) : ViewModel() {

    fun getAllChantiersByUser(ref : Long) : LiveData<List<Chantier>>{
        return repository.getAllChantiersByUser(ref).asLiveData()
    }
}

class ChantierViewModelFactory(private val repository: ChantierRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChantierViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ChantierViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Chaniter inconnu")
    }
}