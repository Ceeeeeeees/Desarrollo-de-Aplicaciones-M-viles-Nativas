package com.escom7cv1.proyectotodo.ui.usuario

import androidx.room.Embedded
import androidx.room.Relation
import com.escom7cv1.proyectotodo.ui.planta.Planta

data class UsuarioconPlanta(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "plantaId",
        entityColumn = "id"
    )
    val planta: Planta?
)
