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

    @Query("SELECT * FROM tarea WHERE (importante = 1 OR urgente = 1) AND completada = 0")
    suspend fun getTareasImportantes(): List<Tarea>

    @Query("UPDATE tarea SET completada = :completada WHERE id = :tareaId")
    suspend fun updateStatusTarea(tareaId: Long, completada: Boolean)

    @Query("DELETE FROM tarea")
    suspend fun deleteAllTareas()

    /*@Query("DELETE FROM tarea WHERE listaId = :listaID")
    suspend fun eliminarTarea(listaID: Long)*/
}