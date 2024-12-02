package com.escom7cv1.proyectotodo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido a Hazlo ¡ya!"
    }
    val text: LiveData<String> = _text
}