package com.escom7cv1.proyectotodo.ui.planta

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planta")
data class Planta (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var etapaCrecimiento: Int = 0,
    var image: String = "semilla",
    val puntosRestauracion: Int = 100
)