package com.escom7cv1.proyectotodo.ui.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsuarioViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {
    private val _user = MutableLiveData<Usuario>()
    val user: LiveData<Usuario> get() = _user

    // Insert
    fun insertUser(user: Usuario) {
        viewModelScope.launch {
            usuarioRepository.insertUser(user)
        }
    }

    // Get user
    fun getUserById(userId: Long) {
        viewModelScope.launch {
            val usuario = usuarioRepository.getUserById(userId)
            _user.postValue(usuario)
        }
    }

    // Update nopacoins
    fun updateUserPoints(userId: Long, nopacoins: Int) {
        viewModelScope.launch {
            usuarioRepository.updateUserPoints(userId, nopacoins)
        }
    }
}