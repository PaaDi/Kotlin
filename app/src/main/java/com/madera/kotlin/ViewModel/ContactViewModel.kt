package com.madera.kotlin.ViewModel

import androidx.lifecycle.*
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.Repository.ContactRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    fun createContact(contact: Contact) = viewModelScope.launch {
        repository.createContact(contact)
    }

    fun getAllContactsByClient(id : Int) : LiveData<List<Contact>>{
        return repository.getAllContactsByClient(id).asLiveData()
    }

    fun isContactExist(ref: Long): Boolean{
        return repository.isContactExist(ref)
    }

    fun deleteContact(contact: Contact){
        repository.deleteContact(contact)
    }
}

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)){
            @Suppress("UNCHECKED_CAS")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Inconnu (Contact)")
    }
}