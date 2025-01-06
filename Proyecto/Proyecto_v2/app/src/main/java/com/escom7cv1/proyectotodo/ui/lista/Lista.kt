package com.escom7cv1.proyectotodo.ui.lista

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.escom7cv1.proyectotodo.ui.tarea.Tarea

@Entity(tableName = "lista")
data class Lista (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String
)