package com.escom7cv1.proyectotodo.ui.lista

import androidx.lifecycle.LiveData
import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.ui.listaTareas.ListaTareas

class ListaRepository(private val appDatabase: AppDatabase) {
    private val listaDao = appDatabase.listaDao()

    suspend fun insertLista(lista: Lista) {
        listaDao.insertLista(lista)
    }

    suspend fun getListas(): LiveData<List<Lista>> {
        return listaDao.getListas()
    }

    suspend fun getListaTareas(listaId: Long): ListaTareas {
        return listaDao.getListaTareas(listaId)
    }
}
