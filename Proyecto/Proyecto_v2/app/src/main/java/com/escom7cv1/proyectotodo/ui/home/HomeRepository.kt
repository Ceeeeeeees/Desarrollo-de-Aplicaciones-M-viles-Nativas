package com.escom7cv1.proyectotodo.ui.home

import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.ui.planta.Planta

class HomeRepository(private val appDatabase: AppDatabase)  {

    private val plantaDAO = appDatabase.plantaDao()

    fun obtenerPlanta() = plantaDAO.obtenerPlanta()

}