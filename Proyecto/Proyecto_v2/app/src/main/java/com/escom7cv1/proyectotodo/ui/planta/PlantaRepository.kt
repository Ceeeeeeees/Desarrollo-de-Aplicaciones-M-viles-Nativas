package com.escom7cv1.proyectotodo.ui.planta

class PlantaRepository(private val plantaDao: PlantaDao) {
    // Get
    suspend fun getPlantaById(plantaId: Long): Planta {
        return plantaDao.getPlantaById(plantaId)
    }

    // Insert
    suspend fun insertPlanta(planta: Planta) {
        plantaDao.insertPlanta(planta)
    }
}