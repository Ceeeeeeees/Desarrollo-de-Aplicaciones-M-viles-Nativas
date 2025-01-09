package com.escom7cv1.proyectotodo.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListaViewModel(private val listaRepository: ListaRepository) : ViewModel() {

    private val _allLists = MutableLiveData<List<Lista>>()
    val allLists: LiveData<List<Lista>> get() = _allLists

    private val _text = MutableLiveData<String>().apply {
            value = "Ingresa el nombre de tu nueva lista"
        }
        val text: LiveData<String> = _text

    init {
        getListas()
    }

    // Insertar lista
    fun insertLista(lista: Lista) {
        viewModelScope.launch {
            listaRepository.insertLista(lista)
        }
    }

    // Get Listas y update LiveData
    private fun getListas() {
        viewModelScope.launch {
            listaRepository.getListas().observeForever { listas ->
                _allLists.postValue(listas)
            }
        }
    }



    // Get Lista con tareas
    fun getListaTareas(listaId: Long) {
        viewModelScope.launch {
            val listaTareas = listaRepository.getListaTareas(listaId)
        }
    }
}
class ListaViewModelFactory(
    private val listaRepository: ListaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaViewModel::class.java)) {
            return ListaViewModel(listaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

