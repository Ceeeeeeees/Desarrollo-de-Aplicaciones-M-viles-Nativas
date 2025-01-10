package com.escom7cv1.proyectotodo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

    fun cargarPlanta () {
        val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
            println("Se murio potter")
            Log.e("CoroutineExceptionHandler", "Error capturado: ${throwable.message}")
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val plantaCargada = repository.obtenerPlanta()
            _planta.postValue(plantaCargada)
        }
    }

    fun actualizarPlanta (planta: Planta) {
        viewModelScope.launch (Dispatchers.IO) {
            val plantaActual = repository.obtenerPlanta() ?: Planta ()
            val puntos = planta.puntos
            plantaActual.etapaCrecimiento = when {
                puntos < 20 -> 1
                puntos in 20..39 -> 2
                puntos in 40..59 -> 3
                puntos in 60..79 -> 4
                puntos in 80..99 -> 5
                else -> 1
            }
            repository.actualizarPlanta(plantaActual)
            _planta.postValue(plantaActual)
        }
    }
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
