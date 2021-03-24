package com.madera.kotlin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.madera.kotlin.Entity.Request
import com.madera.kotlin.Repository.RequestRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RequestViewModel(private val repository: RequestRepository) : ViewModel(){

    fun saveRequest(request: Request) = viewModelScope.launch {
        repository.saveRequest(request)
    }

    fun deleteRequest(request: Request){
        repository.deleteRequest(request)
    }

    fun getAllRequest():List<Request>{
        return repository.getAllRequest()
    }
}

class RequestViewModelFactory(private val repository: RequestRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RequestViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RequestViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Request Inconnu")
    }
}