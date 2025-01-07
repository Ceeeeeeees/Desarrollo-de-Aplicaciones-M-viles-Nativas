package com.escom7cv1.proyectotodo.ui.lista

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.escom7cv1.proyectotodo.ui.listaTareas.ListaTareas

@Dao
interface ListaDao {
    @Insert
    suspend fun insertLista(lista: Lista)

    @Query("SELECT * FROM lista WHERE isDefault = 0")
    fun getListas(): LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM lista WHERE id = :listaId")
    suspend fun getListaTareas(listaId: Long): ListaTareas

    @Query("DELETE FROM lista")
    suspend fun deleteAllListas()
}