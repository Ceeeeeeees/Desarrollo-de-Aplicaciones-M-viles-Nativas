package com.escom7cv1.proyectotodo.ui.home

import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.ui.planta.Planta
import com.escom7cv1.proyectotodo.ui.planta.PlantaDao

class HomeRepository(private val plantaDao: PlantaDao)  {

    fun obtenerPlanta() : Planta {
        return  plantaDao.obtenerPlanta() ?: Planta(etapaCrecimiento = 1)
    }

    fun actualizarPlanta(planta: Planta) {
        plantaDao.actualizarPlanta(planta)
    }
}