package com.escom7cv1.proyectotodo.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListaViewModel : ViewModel() {

        private val _text = MutableLiveData<String>().apply {
            value = "Ingresa el nombre de tu nueva lista"
        }
        val text: LiveData<String> = _text
}