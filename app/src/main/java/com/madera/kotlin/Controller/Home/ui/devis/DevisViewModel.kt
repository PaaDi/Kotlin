package com.madera.kotlin.Controller.Home.ui.devis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevisViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Espace Devis !"
    }
    val text: LiveData<String> = _text
}