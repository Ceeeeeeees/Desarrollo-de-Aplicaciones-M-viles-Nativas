/**
 *  Práctica 9 - Ordenar Pizzas
 *
 *  @author Saucedo Moreno César Enrique
 *  @group 7CV1
 *  @date 2024-11-26
 *  @versión 1.0
 */

package com.escom7cv1.practica9_saucedocesar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.escom7cv1.practica9_saucedocesar.databinding.ActivityAgradecimientosBinding

class Agradecimientos : AppCompatActivity() {
    private lateinit var binding: ActivityAgradecimientosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agradecimientos)
        binding = ActivityAgradecimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.resultadoNombreID.text = intent.getStringExtra("Nombre")
        binding.resultadoTelefonoID.text = intent.getStringExtra("Telefono")
        binding.resultadoTamanioID.text = intent.getStringExtra("Tamanio")
        binding.resultadoFechaID.text = intent.getStringExtra("Fecha")
        binding.resultadoHoraID.text = intent.getStringExtra("Hora")

        binding.botonCalificar.setOnClickListener{
            binding.calificandoTexto.text = binding.ratingBar.rating.toString()
        }
    }
}


/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 **/