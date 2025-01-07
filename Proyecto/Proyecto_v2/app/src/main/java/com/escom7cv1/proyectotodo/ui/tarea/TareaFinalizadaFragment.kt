package com.escom7cv1.proyectotodo.ui.tarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.escom7cv1.proyectotodo.databinding.FragmentTareaFinalizadaBinding

class TareaFinalizadaFragment : Fragment() {

    private var _binding: FragmentTareaFinalizadaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTareaFinalizadaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Obtener los argumentos
        val nombre = arguments?.getString("nombre")
        val nopacoins = arguments?.getInt("nopacoins")

        val textViewNombre: TextView = binding.nombreTareaCompletada
        val textViewNopacoins: TextView = binding.nopacoins

        textViewNombre.text = nombre
        textViewNopacoins.text = nopacoins.toString()


        val aceptarButton: Button = binding.aceptarNopacoins

        aceptarButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }

}