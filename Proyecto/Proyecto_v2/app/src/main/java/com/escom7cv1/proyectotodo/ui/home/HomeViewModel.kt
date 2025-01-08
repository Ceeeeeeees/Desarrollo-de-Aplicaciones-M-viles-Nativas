package com.escom7cv1.proyectotodo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escom7cv1.proyectotodo.ui.planta.Planta
import com.escom7cv1.proyectotodo.ui.planta.PlantaRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _planta = MutableLiveData<Planta>()
    val planta: LiveData<Planta> = _planta

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido a Hazlo ¡ya!"
    }
    val text: LiveData<String> = _text

    /*fun obtenerPlanta() {
        val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
            Log.e("CoroutineExceptionHandler", "Error capturado: ${throwable.message}")
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            _planta.postValue(repository.obtenerPlanta())
        }
    }*/
}

class HomeViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
