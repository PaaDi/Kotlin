package com.madera.kotlin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.Entity.Projet
import com.madera.kotlin.Repository.ProjetRepository
import java.lang.IllegalArgumentException

class ProjetViewModel(private val repository: ProjetRepository) : ViewModel() {
    fun getProjectByRef(ref: Long) : Projet{
        return repository.getProjectByRef(ref)
    }
}

class ProjetViewModelFactory(private val repository: ProjetRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjetViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProjetViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Projet inconnu")
    }
}