package com.escom7cv1.proyectotodo.ui.planta

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantaDao {
    @Insert
    suspend fun insertPlanta(tree: Planta)

    @Query("SELECT * FROM planta WHERE id = :plantaId")
    suspend fun getPlantaById(plantaId: Long): Planta
}