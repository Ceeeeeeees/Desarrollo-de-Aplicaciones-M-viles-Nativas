package com.escom7cv1.proyectotodo.ui.tarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentGalleryBinding
import com.escom7cv1.proyectotodo.databinding.FragmentTareaBinding

class TareaFragment : Fragment() {

    private var _binding: FragmentTareaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTareaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Obtener los argumentos
        val nombre = arguments?.getString("nombre")
        val fechaInicio = arguments?.getString("fechaInicio")
        val fechaFin = arguments?.getString("fechaFin")

        val textViewNombre: TextView = binding.nombreTarea
        val textViewFechaInicio: TextView = binding.fechaInicio
        val textViewFechaFin: TextView = binding.fechaFin

        textViewNombre.text = nombre
        textViewFechaInicio.text = fechaInicio
        textViewFechaFin.text = fechaFin

        return root
    }
}