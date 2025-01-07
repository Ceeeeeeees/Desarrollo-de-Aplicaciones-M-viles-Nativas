package com.escom7cv1.proyectotodo.ui.tarea

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TareaDao {
    @Insert
    suspend fun insertTarea(tarea: Tarea)

    @Query("SELECT * FROM tarea WHERE listaId = :listaId AND completada = 0")
    suspend fun getTareasPorLista(listaId: Long): List<Tarea>

    @Query("UPDATE tarea SET completada = :completada WHERE id = :tareaId")
    suspend fun updateStatusTarea(tareaId: Long, completada: Boolean)

    @Query("DELETE FROM tarea")
    suspend fun deleteAllTareas()
}