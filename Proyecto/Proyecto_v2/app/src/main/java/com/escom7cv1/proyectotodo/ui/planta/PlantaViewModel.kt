package com.escom7cv1.proyectotodo.ui.planta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModel
import kotlinx.coroutines.launch

class PlantaViewModel(private val plantaRepository: PlantaRepository) : ViewModel(){
    private val _tree = MutableLiveData<Planta>()
    val tree: LiveData<Planta> get() = _tree

    // Insert
    fun insertPlanta(planta: Planta) {
        viewModelScope.launch {
            plantaRepository.insertPlanta(planta)
        }
    }

    // Get
    fun getPlantaById(plantaId: Long) {
        viewModelScope.launch {
            val planta = plantaRepository.getPlantaById(plantaId)
            _tree.postValue(planta)
        }
    }

    suspend fun getPuntos() = plantaRepository.getPuntos() ?: Planta()

    suspend fun updatePuntos(planta: Planta) {
        plantaRepository.actualizarPlanta(planta)
    }
}
class PlantaViewModelFactory(
    private val PlantaRepository: PlantaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantaViewModel::class.java)) {
            return PlantaViewModel(PlantaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}