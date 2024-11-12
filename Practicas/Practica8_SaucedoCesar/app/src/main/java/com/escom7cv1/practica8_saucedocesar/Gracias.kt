/**
 *   Practica 8 - Crear una aplicación simple de entrega de paquetes.
 *
 *   Esta es la clase Gracias.kt, la cual se encarga de mostrar un mensaje de agradecimiento al usuario
 *
 *
 *   @autor: Saucedo Moreno César Enrique
 *   @materia: Desarrollo de aplicaciones móviles nativas
 *   @grupo 7CV1
 *   @fecha 12 de Noviembre de 2024
**/

package com.escom7cv1.practica8_saucedocesar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Gracias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gracias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 **/