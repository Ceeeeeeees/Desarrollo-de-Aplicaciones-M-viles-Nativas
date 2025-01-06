package com.escom7cv1.proyectotodo.ui.usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertUser(user: Usuario)

    @Query("SELECT * FROM usuario WHERE id = :userId")
    suspend fun getUserById(userId: Long): Usuario

    @Query("UPDATE usuario SET nopacoins = :nopacoins WHERE id = :userId")
    suspend fun updateUserPoints(userId: Long, nopacoins: Int)

    @Transaction
    @Query("SELECT * FROM usuario WHERE id = :userId")
    suspend fun getUsuarioConPlanta(userId: Long): UsuarioconPlanta
}