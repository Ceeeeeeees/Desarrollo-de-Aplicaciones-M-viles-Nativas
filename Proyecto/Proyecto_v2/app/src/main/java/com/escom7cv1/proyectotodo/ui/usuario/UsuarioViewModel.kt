package com.escom7cv1.proyectotodo.ui.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModel
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

    fun getNopacois(): Int{
        var nopacoins = 0
        viewModelScope.launch {
            nopacoins = usuarioRepository.getNopacois()
        }
        return nopacoins
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

class UsuarioViewModelFactory(
    private val usuarioRepository: UsuarioRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            return UsuarioViewModel(usuarioRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}