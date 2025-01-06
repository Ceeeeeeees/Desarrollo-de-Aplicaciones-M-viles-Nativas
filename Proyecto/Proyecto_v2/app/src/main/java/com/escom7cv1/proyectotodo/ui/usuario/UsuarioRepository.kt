package com.escom7cv1.proyectotodo.ui.usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    // Get user
    suspend fun getUserById(userId: Long): Usuario {
        return usuarioDao.getUserById(userId)
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