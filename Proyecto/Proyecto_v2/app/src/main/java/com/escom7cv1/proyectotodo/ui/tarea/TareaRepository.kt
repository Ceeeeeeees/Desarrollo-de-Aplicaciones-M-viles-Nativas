package com.escom7cv1.proyectotodo.ui.tarea

class TareaRepository(private val tareaDao: TareaDao) {
    // Get tareas de lista específica
    suspend fun getTareasPorLista(listaId: Long): List<Tarea> {
        return tareaDao.getTareasPorLista(listaId)
    }

    // Insert
    suspend fun insertTarea(tarea: Tarea) {
        tareaDao.insertTarea(tarea)
    }

    // Update
    suspend fun updateStatusTarea(tareaId: Long, completada: Boolean) {
        tareaDao.updateStatusTarea(tareaId, completada)
    }
}