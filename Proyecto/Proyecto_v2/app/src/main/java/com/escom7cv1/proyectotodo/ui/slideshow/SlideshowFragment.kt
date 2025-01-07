package com.escom7cv1.proyectotodo.ui.slideshow

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
import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentSlideshowBinding
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModel
import com.escom7cv1.proyectotodo.ui.tarea.Tarea
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModel
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModelFactory

class SlideshowFragment : Fragment() {

private var _binding: FragmentSlideshowBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private lateinit var tareaViewModel : TareaViewModel
    private lateinit var listaViewModel: ListaViewModel
    private var listaId: Long = 0

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
      val root: View = binding.root

    //tareaViewModel = ViewModelProvider(this).get(TareaViewModel::class.java)
    listaViewModel = ViewModelProvider(this).get(ListaViewModel::class.java)

    listaId = arguments?.getLong("listaId") ?: 0

    binding.aniadirTareaImportante.setOnClickListener {
      findNavController().navigate(R.id.nav_crearTarea)
    }

    tareaViewModel.getTareasPorLista(listaId)
    tareaViewModel.tareas.observe(viewLifecycleOwner) { tareas ->
      mostrarTareas(tareas)
    }

    return root
  }

  private fun mostrarTareas(tareas: List<Tarea>) {
    val contenedor : LinearLayout = binding.contenedorTareasImportantes
    contenedor.removeAllViews()
    for (tarea in tareas) {
      val checkBox = CheckBox(context).apply {
        text = tarea.nombre
        isChecked = tarea.completada
        setOnCheckedChangeListener { _, isChecked ->
          tareaViewModel.updateStatusTarea(tarea.id, isChecked)
          val mensaje = if (isChecked) "Tarea completada" else "Tarea pendiente"
          Toast.makeText(context, "Tarea ${tarea.nombre}: $mensaje", Toast.LENGTH_SHORT).show()
        }
      }
      contenedor.addView(checkBox)
    }
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}