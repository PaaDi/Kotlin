package com.madera.kotlin.ViewModel

import androidx.lifecycle.*
import com.madera.kotlin.Entity.Chantier
import com.madera.kotlin.Repository.ChantierRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ChantierViewModel(private val repository: ChantierRepository) : ViewModel() {

    fun getAllChantiersByUser(ref : Int) : LiveData<List<Chantier>>{
        return repository.getAllChantiersByUser(ref).asLiveData()
    }

    fun createChantier(chantier: Chantier) = viewModelScope.launch {
        repository.createChantier(chantier)
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