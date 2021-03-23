package com.madera.kotlin.ViewModel

import androidx.lifecycle.*
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.Repository.ClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {

    // LiveData permet d'ajouter un observer aux données et fiare un update de l'interface uniquement quand les données sont modifiéres
    val AllClients: LiveData<List<Client>> = repository.allClients.asLiveData()

    fun getClientById(id: Int?) : Client {
        return repository.getClientById(id)
    }

    fun getClientByRef(refCli: Long) : Client{
        return repository.getClientByRef(refCli)
    }

    // Vérifier que le client existe via sa réf
    fun isClientExist(ref : Long) : Boolean{
        return repository.isClientExist(ref)
    }

    // Mise à jour du client
    fun updateClient(ref: Long, nomUP : String, adresseUP : String, codePostalUP: Int, villeUP: String,proUP: Boolean, secteurUP : String, description: String){
        repository.updateClient(ref, nomUP, adresseUP, codePostalUP, villeUP, proUP, secteurUP, description)
    }


    // ViewModelScope permet de lancer un nouvelle coroutine pour insérer la data de manière non bloquante
    fun createClient(client: Client) = viewModelScope.launch {
        repository.createClient(client)
    }

    fun deleteClient(client: Client) {
        repository.deleteClient(client)
    }
}

class ClientViewModelFactory(private val repository: ClientRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ClientViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Client inconnu")
    }
}