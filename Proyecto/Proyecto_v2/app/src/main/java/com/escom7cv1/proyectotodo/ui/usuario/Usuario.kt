package com.escom7cv1.proyectotodo.ui.usuario

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.escom7cv1.proyectotodo.ui.planta.Planta

@Entity(tableName = "usuario")
data class Usuario (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var nopacoins: Int = 0,
    var planta: Planta = Planta(id = 0)
)