package com.escom7cv1.proyectotodo.ui.planta

import androidx.room.Query
import com.escom7cv1.proyectotodo.AppDatabase

class PlantaRepository(private val appDatabase: AppDatabase) {
    private val plantaDao = appDatabase.plantaDao()

    suspend fun getPlantaById(plantaId: Long): Planta {
        return plantaDao.getPlantaById(plantaId)
    }

    // Insert
    suspend fun insertPlanta(planta: Planta) {
        plantaDao.insertPlanta(planta)
    }

    suspend fun getPuntos(): Planta? {
        return plantaDao.obtenerPlanta()
    }

    suspend fun updatePuntos(puntos: Int) {
        plantaDao.updatePuntos(puntos)
    }

    suspend fun actualizarPlanta(planta: Planta) {
        plantaDao.actualizarPlanta(planta)
    }

}