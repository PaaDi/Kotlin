package com.madera.kotlin.Controller.Home.ui.chantier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClientViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Espace Client !"
    }
    val text: LiveData<String> = _text
}