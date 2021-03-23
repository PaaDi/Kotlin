package com.madera.kotlin.ViewModel

import androidx.lifecycle.*
import com.madera.kotlin.Entity.Projet
import com.madera.kotlin.Repository.ProjetRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProjetViewModel(private val repository: ProjetRepository) : ViewModel() {
    fun getProjectByRef(ref: Long) : Projet{
        return repository.getProjectByRef(ref)
    }

    fun getAllProjetsByUser(id: Int) : LiveData<List<Projet>>{
        return repository.getAllProjetsByUser(id).asLiveData()
    }
    fun isProjetExist(refProj: Long) : Boolean{
        return repository.isProjetExist(refProj)
    }

    fun createProject(projet: Projet) = viewModelScope.launch {
        repository.createProject(projet)
    }

    fun updateProjet(clientId: Int?,nomProjet :String,notes:String,refProj: Long){
        repository.updateProjet(clientId, nomProjet, notes, refProj)
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