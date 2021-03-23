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

    fun isChantierExist(ref:Long) : Boolean{
        return repository.isChantierExist(ref)
    }

    fun updateChantier(projetId : Int?, userId : Int, nom:String,adresse:String,codePostal:Int,ville:String,notes:String,datecreation:String,datelancement:String, refChantier : Long){
        repository.updateChantier(projetId, userId, nom, adresse, codePostal, ville, notes, datecreation, datelancement, refChantier)
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