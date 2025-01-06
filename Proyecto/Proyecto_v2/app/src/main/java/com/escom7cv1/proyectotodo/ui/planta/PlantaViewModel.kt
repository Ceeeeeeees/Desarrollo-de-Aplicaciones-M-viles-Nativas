package com.escom7cv1.proyectotodo.ui.planta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}