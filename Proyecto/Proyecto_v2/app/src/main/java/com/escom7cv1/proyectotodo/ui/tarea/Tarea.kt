package com.escom7cv1.proyectotodo.ui.tarea

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.escom7cv1.proyectotodo.ui.lista.Lista
import java.util.Date

@Entity(
    tableName = "tarea",
    foreignKeys = [ForeignKey(
        entity = Lista::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("listaId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Tarea (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val urgente: Boolean,
    val importante: Boolean,
    val fechaInicio: String,
    val fechaFin: String,
    val completada: Boolean = false,
    val puntos: Int = 20,
    @ColumnInfo(index = true) val listaId: Long // Liga la tarea a una lista
)