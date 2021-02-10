package com.madera.kotlin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.madera.kotlin.Entity.User
import com.madera.kotlin.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // Lancement d'une coroutine pour ajouter un utilisateurs de manière non bloquante
    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun connectUser(login: String, pass: String) = viewModelScope.launch {
        repository.connectUser(login,pass)
    }
}

/**
 * Permet de tenir compte du cycle de vie de Model afin d'avoir toujours la bonne instance de UserViewModel
 */
class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            @Suppress("UNCHECKED_CAS")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Inconnu")
    }
}