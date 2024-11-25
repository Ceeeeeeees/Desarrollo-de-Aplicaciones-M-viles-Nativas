package com.escom7cv1.practica9_saucedocesar

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.SeekBar
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.escom7cv1.practica9_saucedocesar.databinding.ActivityMainBinding
import java.sql.Time

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var slider: SeekBar = binding.pizzaID
        var value: TextView = binding.tamanioPizza

        val tamanioPizas = arrayListOf<String>(
            "Por favor selecciona una",
            "$99 - Chica", "$120 - Mediana", "$200 - Extra-Grande"
        )

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text = tamanioPizas[progress]
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.fechaBoton.setOnClickListener {
            val calendario = Calendar.getInstance()
            val dia = calendario.get(Calendar.DAY_OF_MONTH)
            val mes = calendario.get(Calendar.MONTH)
            val anio = calendario.get(Calendar.YEAR)

            val miOrdenFecha = DatePickerDialog(
                this,
                android.R.style.ThemeOverlay,
                DatePickerDialog.OnDateSetListener { DatePicker,
                                                     anio, mes, dia ->
                    binding.fechaTexto.text = "$dia/ ${mes + 1} / $anio"
                },
                anio,
                mes,
                dia
            )
            miOrdenFecha.show()
        }
        binding.horaBoton.setOnClickListener{
            val calendario = Calendar.getInstance()
            val hora = calendario.get(Calendar.HOUR_OF_DAY)
            val minuto = calendario.get(Calendar.MINUTE)

            val miOrdenHora = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener{
                    TimePicker, hourOfDay, minute ->
                    binding.horaTexto.text = "$hourOfDay : $minute"},
                hora,
                minuto,
                true)
            miOrdenHora.show()
        }
        binding.programarOrdenBoton.setOnClickListener{
            val tamanio = binding.tamanioPizza.text.toString()
            println(tamanio)
            var programarOrden = Intent(this,Agradecimientos::class.java)
            programarOrden.putExtra("Nombre",binding.nombreID.text.toString())
            programarOrden.putExtra("Telefono",binding.telefonoID.text.toString())
            programarOrden.putExtra("Tamanio", binding.tamanioPizza.text.toString())
            programarOrden.putExtra("Fecha",binding.fechaTexto.text.toString())
            programarOrden.putExtra("Hora",binding.horaTexto.text.toString())
            startActivity(programarOrden)
        }
    }
}
