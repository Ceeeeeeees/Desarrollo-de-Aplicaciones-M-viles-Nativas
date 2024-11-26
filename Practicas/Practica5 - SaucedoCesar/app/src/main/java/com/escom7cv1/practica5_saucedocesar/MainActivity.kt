package com.escom7cv1.practica5_saucedocesar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.escom7cv1.practica5_saucedocesar.databinding.ActivityMainBinding

/**
 *  Práctica 5 - Calculadora
 *
 *  @author Saucedo Moreno César Enrique
 *  @group 7CV1
 *  @date 2024-10-15
 *  @versión 1.0
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Suma
        binding.buttonSuma.setOnClickListener {
            val numero1 = binding.editTextTextNum1.text.toString().toDoubleOrNull() ?: 0.0
            val numero2 = binding.editTextTextNum2.text.toString().toDoubleOrNull() ?: 0.0
            val resultado = numero1 + numero2
            binding.textViewResultado.text = resultado.toString()
        }

        // Resta
        binding.buttonResta.setOnClickListener {
            val numero1 = binding.editTextTextNum1.text.toString().toDoubleOrNull() ?: 0.0
            val numero2 = binding.editTextTextNum2.text.toString().toDoubleOrNull() ?: 0.0
            val resultado = numero1 - numero2
            binding.textViewResultado.text = resultado.toString()
        }

        // Multiplicación
        binding.buttonMulti.setOnClickListener {
            val numero1 = binding.editTextTextNum1.text.toString().toDoubleOrNull() ?: 0.0
            val numero2 = binding.editTextTextNum2.text.toString().toDoubleOrNull() ?: 0.0
            val resultado = numero1 * numero2
            binding.textViewResultado.text = resultado.toString()
        }

        // División
        binding.buttonDiv.setOnClickListener {
            val numero1 = binding.editTextTextNum1.text.toString().toDoubleOrNull() ?: 0.0
            val numero2 = binding.editTextTextNum2.text.toString().toDoubleOrNull() ?: 0.0
            if (numero2 != 0.0) {
                val resultado = numero1 / numero2
                binding.textViewResultado.text = resultado.toString()
            } else {
                binding.textViewResultado.text = "Error: División por cero"
            }
        }
    }
}

/**
 *  SAUCEDO MORENO CÉSAR ENRIQUE 7CV1
 */