package com.escom7cv1.proyectotodo.ui.tarea

import com.escom7cv1.proyectotodo.AppDatabase

class TareaRepository(private val appDatabase: AppDatabase) {
    private val tareaDao = appDatabase.tareaDao()

    // Get tareas de lista específica
    suspend fun getTareasPorLista(listaId: Long): List<Tarea> {
        return tareaDao.getTareasPorLista(listaId)
    }

    // Insert
    suspend fun insertTarea(tarea: Tarea) {
        tareaDao.insertTarea(tarea)
    }

    // Get tareas importantes o urgentes
    suspend fun getTareasImportantes(): List<Tarea> {
        return tareaDao.getTareasImportantes()
    }

    // Update
    suspend fun updateStatusTarea(tareaId: Long, completada: Boolean) {
        tareaDao.updateStatusTarea(tareaId, completada)
    }



}