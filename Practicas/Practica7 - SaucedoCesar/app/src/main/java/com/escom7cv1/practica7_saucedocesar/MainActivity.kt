/*
*    Creado por Cesar Saucedo, 2021
*    Practica 7
*    @autor Saucedo Moreno César Enrique
*    @fecha 29 de Octubre, 2024
*    @version: 1.0
*/
package com.escom7cv1.practica7_saucedocesar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.escom7cv1.practica7_saucedocesar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    fun displayMensaje(view: View) {
        binding.informacion.text = "Android Application Development, Android Security Essentials and Monetize Android Applications"
    }
}