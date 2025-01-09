package com.escom7cv1.proyectotodo.ui.usuario

import androidx.room.Query
import com.escom7cv1.proyectotodo.AppDatabase

class UsuarioRepository(private val appDatabase: AppDatabase) {
    private val usuarioDao = appDatabase.usuarioDao()

    // Get user
    suspend fun getUserById(userId: Long): Usuario {
        return usuarioDao.getUserById(userId)
    }

    suspend fun getNopacois(): Int {
        return usuarioDao.getNopacoins()
    }

    // Insert
    suspend fun insertUser(user: Usuario) {
        usuarioDao.insertUser(user)
    }

    // Update nopacoins
    suspend fun updateUserPoints(userId: Long, nopacoins: Int) {
        usuarioDao.updateUserPoints(userId, nopacoins)
    }
}