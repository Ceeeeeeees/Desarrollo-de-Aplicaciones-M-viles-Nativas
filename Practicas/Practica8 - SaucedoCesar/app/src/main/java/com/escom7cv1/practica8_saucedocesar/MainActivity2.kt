/**
 *    Practica 8 - Crear una aplicación simple de entrega de paquetes.
 *
 *    Este archivo tiene la clase Confirmacion, la cual se encarga de los datos ingresados por el
 *    usuario y permite regresar a la pantalla principal para editarlos.
 *
 *    @autor: Saucedo Moreno César Enrique
 *    @materia: Desarrollo de aplicaciones móviles nativas
 *    @grupo 7CV1
 *    @fecha 12 de Noviembre de 2024
**/

package com.escom7cv1.practica8_saucedocesar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.escom7cv1.practica8_saucedocesar.databinding.ActivityMain2Binding

class Confirmacion : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.SnombreID.text = intent.getStringExtra("Nombre")
        binding.Sdireccion.text = intent.getStringExtra("Dirección")
        binding.Sciudad.text = intent.getStringExtra("Ciudad")
        binding.Sestado.text = intent.getStringExtra("Estado")
        binding.ScodigoPostal.text = intent.getStringExtra("Código Postal")
        binding.Spais.text = intent.getStringExtra("País")

        botonAceptarClick()
        botonEditarClick()
    }

    fun botonAceptarClick(){
        binding.botonAceptar.setOnClickListener {
            var Aceptada = Intent(this, Gracias::class.java)
            startActivity(Aceptada)
        }
    }

    fun botonEditarClick(){
        binding.botonEditar.setOnClickListener {
            onBackPressed()
        }
    }
}

/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 **/