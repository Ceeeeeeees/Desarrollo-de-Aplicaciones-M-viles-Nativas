package com.escom7cv1.proyectotodo.ui.tarea

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.databinding.FragmentCreartareaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class CrearTareaFragment : Fragment() {

    private var _binding: FragmentCreartareaBinding? = null
    private val binding get() = _binding!!

    private lateinit var tareaViewModel: TareaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tareaViewModel = ViewModelProvider(this).get(TareaViewModel::class.java)


        _binding = FragmentCreartareaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tituloTarea: EditText = binding.tituloTarea
        val urgenteCheckBox: CheckBox = binding.urgente
        val importanteCheckBox: CheckBox = binding.importante
        val fechaInicioTarea: TextView = binding.fechaInicioTarea
        val btnFechaInicioTarea: Button = binding.btnFechaInicioTarea
        val fechaFinTarea: TextView = binding.fechaFinTarea
        val btnFechaFinTarea: Button = binding.btnFechaFinTarea
        val guardarTareaBoton: Button = binding.GuardarTarea

        btnFechaInicioTarea.setOnClickListener {
            showDatePicker(fechaInicioTarea)
        }

        btnFechaFinTarea.setOnClickListener {
            showDatePicker(fechaFinTarea)
        }

        guardarTareaBoton.setOnClickListener {
            val tareaTitulo = tituloTarea.text.toString()
            val isUrgente = urgenteCheckBox.isChecked
            val isImportante = importanteCheckBox.isChecked
            val tareaFechaInicio = fechaInicioTarea.text.toString()
            val tareaFechaFin = fechaFinTarea.text.toString()

            // Aquí se debería guardar la tarea en la base de datos
            if (tareaTitulo.isNotEmpty() && tareaFechaInicio.isNotEmpty() && tareaFechaFin.isNotEmpty()) {

                val tarea = Tarea(
                    nombre = tareaTitulo,
                    urgente = isUrgente,
                    importante = isImportante,
                    fechaInicio = tareaFechaInicio,
                    fechaFin = tareaFechaFin,
                    listaId = 1 // cambiar por el id correcto
                )

                tareaViewModel.insertTarea(tarea)
                limpiarCampos()

                Toast.makeText(requireContext(), "Tarea '$tareaTitulo' guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }




        return root
    }

    private fun showDatePicker(textView: TextView) {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val date = DatePickerDialog(requireContext(), android.R.style.ThemeOverlay, DatePickerDialog.OnDateSetListener { _, Year, Month, Day ->
            textView.text = "$Day / ${Month + 1} / $Year"
        }, year, month, day)
        date.show()
    }

    private fun limpiarCampos() {
        binding.tituloTarea.text.clear()
        binding.urgente.isChecked = false
        binding.importante.isChecked = false
        //binding.fechaInicioTarea.text.clear()
        //binding.fechaFinTarea.text.clear()
    }

}

class CreartareaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is crearTarea Fragment"
    }
    val text: LiveData<String> = _text

}
