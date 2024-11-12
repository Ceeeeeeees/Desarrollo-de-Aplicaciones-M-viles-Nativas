/*
 *  Practica 6: Crear una app que permita ordenar una pizza con diferentes ingredientes y tamaños
 *  @author Saucedo Moreno César Enrique
 *  @group 7CV1
 *  @date 2024-10-15
 *  @versión 1.0
 */

package com.escom7cv1.practica6_saucedo_cesar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.escom7cv1.practica6_saucedo_cesar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        orderPizzaButtonClicked()
    }

    fun orderPizzaButtonClicked() {
        binding.buttonOrdenar.setOnClickListener {
            var tamanioPizzaPrecio = 0.0
            var topingsPrecio = 0.0

            when {
                binding.pizzaChica.isChecked -> tamanioPizzaPrecio = 100.0
                binding.pizzaMediana.isChecked -> tamanioPizzaPrecio = 150.0
                binding.pizzaGrande.isChecked -> tamanioPizzaPrecio = 200.0
            }

            if (binding.checkBoxCebolla.isChecked) {
                topingsPrecio += 10.0
            }
            if (binding.checkBoxAceitunas.isChecked) {
                topingsPrecio += 12.0
            }
            if (binding.checkBoxJitomate.isChecked) {
                topingsPrecio += 20.0
            }
            var totalOrden = topingsPrecio + tamanioPizzaPrecio
            binding.textViewPrecio.text = "El precio de tu orden es de : $$totalOrden"
        }
    }
}

/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 **/
