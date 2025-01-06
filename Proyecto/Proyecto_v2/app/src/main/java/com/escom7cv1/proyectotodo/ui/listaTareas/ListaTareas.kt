package com.escom7cv1.proyectotodo.ui.listaTareas

import androidx.room.Embedded
import androidx.room.Relation
import com.escom7cv1.proyectotodo.ui.lista.Lista
import com.escom7cv1.proyectotodo.ui.tarea.Tarea

data class ListaTareas (
    @Embedded
    val lista: Lista,
    @Relation(
        parentColumn = "id",
        entityColumn = "listaId"
    )
    val tareas: List<Tarea>
)