/**
 *    Practica 8 - Crear una aplicación simple de entrega de paquetes.
 *
 *    Este archivo tiene la clase MainActivity, la cual se encarga de los datos ingresados por el
 *    usuario y permite enviarlos a la pantalla de confirmación.
 *
 *    @autor: Saucedo Moreno César Enrique
 *    @materia: Desarrollo de aplicaciones móviles nativas
 *    @grupo 7CV1
 *    @fecha 12 de Noviembre de 2024
 **/

package com.escom7cv1.practica8_saucedocesar

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.escom7cv1.practica8_saucedocesar.databinding.ActivityMainBinding
import com.escom7cv1.practica8_saucedocesar.Confirmacion

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cronogramaButtonClicked()
    }

    fun cronogramaButtonClicked() {
        binding.buttonCronograma.setOnClickListener {
            val intento = Intent(this, Confirmacion::class.java)
            intento.putExtra("Nombre", binding.editTextTextNombrePersona.text.toString())
            intento.putExtra("Dirección", binding.editTextTextDireccion.text.toString())
            intento.putExtra("Ciudad", binding.editTextTextCiudad.text.toString())
            intento.putExtra("Estado", binding.editTextTextEstado.text.toString())
            intento.putExtra("Código Postal", binding.editTextTextCodigoPostal.text.toString())
            intento.putExtra("País", binding.editTextTextPais.text.toString())
            startActivity(intento)
        }
    }
}

/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 **/