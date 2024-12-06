package com.escom7cv1.proyectotodo.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentGalleryBinding
import com.escom7cv1.proyectotodo.ui.crearTarea.CrearTareaFragment

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        val contenedorTareas: LinearLayout = binding.contenedorTarea
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Las tareas se añadirán dinámicamente
        val tareas = listOf(
            Tarea("Comprar leche", "05/12/2024", "06/12/2024"),
            Tarea("Hacer ejercicio", "05/12/2024", "10/12/2024"),
            Tarea("Estudiar programación", "05/12/2024", "15/12/2024"),
            Tarea("Reunión de trabajo", "06/12/2024", "06/12/2024"),
            Tarea("Llamar a mamá", "06/12/2024", "06/12/2024"),
            Tarea("Leer libro", "07/12/2024", "10/12/2024")
        )


        for (tarea in tareas) {
            val linearLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            val checkBox = CheckBox(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        Toast.makeText(context, "Tarea completada: ${tarea.nombre}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Tarea desmarcada: ${tarea.nombre}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            val textView = TextView(requireContext()).apply {
                text = tarea.nombre
                textSize = 16f
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                setOnClickListener {
                    val bundle = Bundle().apply {
                        putString("nombre", tarea.nombre)
                        putString("fechaInicio", tarea.fechaInicio)
                        putString("fechaFin", tarea.fechaFin)
                    }
                    findNavController().navigate(R.id.nav_tareaDetalles, bundle)
                }
            }

            linearLayout.addView(checkBox)
            linearLayout.addView(textView)

            contenedorTareas.addView(linearLayout)
        }

        val aniadirTareaBoton: Button = binding.aniadirTarea
        /*
        aniadirTareaBoton.setOnClickListener {
            val crearTareaFragment = CrearTareaFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, crearTareaFragment)
                .addToBackStack(null)
                .commit()
        }
        */
        aniadirTareaBoton.setOnClickListener {
            findNavController().navigate(R.id.nav_crearTarea)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class Tarea(
    val nombre: String,
    val fechaInicio: String,
    val fechaFin: String
)
