package com.escom7cv1.proyectotodo.ui.planta

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    @Query("SELECT puntos FROM planta where id = 1")
    suspend  fun getPuntos(): Int

    @Query("UPDATE planta set puntos = :puntos where id = 1")
    suspend fun updatePuntos(puntos: Int)

    @Query("DELETE FROM planta")
    fun eliminarPlantas()

    @Update
    fun actualizarPlanta(planta: Planta)
}