package com.escom7cv1.proyectotodo.ui.tarea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TareaViewModel (private val tareaRepository: TareaRepository) : ViewModel() {
    private val _tareas = MutableLiveData<List<Tarea>>()
    val tareas: LiveData<List<Tarea>> get() = _tareas

    // Insert
    fun insertTarea(tarea: Tarea) {
        viewModelScope.launch {
            tareaRepository.insertTarea(tarea)
        }
    }

    // Get tareas de lista específica
    fun getTareasPorLista(listaId: Long) {
        viewModelScope.launch {
            val tareasList = tareaRepository.getTareasPorLista(listaId)
            _tareas.postValue(tareasList)
        }
    }

    // Update
    fun updateStatusTarea(tareaId: Long, completada: Boolean) {
        viewModelScope.launch {
            tareaRepository.updateStatusTarea(tareaId, completada)
        }
    }
}

class TareaViewModelFactory(
    private val tareaRepository: TareaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TareaViewModel::class.java)) {
            return TareaViewModel(tareaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}