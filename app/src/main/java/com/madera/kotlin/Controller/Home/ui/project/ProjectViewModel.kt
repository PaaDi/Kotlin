package com.madera.kotlin.Controller.Home.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Espace Projet !"
    }
    val text: LiveData<String> = _text
}