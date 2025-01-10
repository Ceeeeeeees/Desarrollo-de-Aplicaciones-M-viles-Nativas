package com.escom7cv1.proyectotodo.ui.usuario

import androidx.room.Embedded
import com.escom7cv1.proyectotodo.ui.planta.Planta
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(
    tableName = "usuario",
    foreignKeys = [ForeignKey(
        entity = Planta::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("plantaId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["plantaId"])]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var nopacoins: Int = 0,
    var plantaId: Long? = null
)


