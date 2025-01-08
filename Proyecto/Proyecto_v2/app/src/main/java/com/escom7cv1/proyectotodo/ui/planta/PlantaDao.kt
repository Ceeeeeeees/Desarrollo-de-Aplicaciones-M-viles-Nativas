package com.escom7cv1.proyectotodo.ui.planta

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.escom7cv1.proyectotodo.ui.lista.Lista

@Dao
interface PlantaDao {
    @Insert
    suspend fun insertPlanta(tree: Planta)

    @Query("SELECT * FROM planta WHERE id = :plantaId")
    suspend fun getPlantaById(plantaId: Long): Planta

    @Query("SELECT * FROM planta LIMIT 1")
    fun obtenerPlanta(): Planta?

    @Query("SELECT * FROM planta")
    fun getPlantas(): LiveData<List<Planta>>

    @Query("SELECT * FROM planta")
    fun obtenerTodasLasPlantas(): List<Planta>?

    @Query("DELETE FROM planta")
    fun eliminarPlantas()
}