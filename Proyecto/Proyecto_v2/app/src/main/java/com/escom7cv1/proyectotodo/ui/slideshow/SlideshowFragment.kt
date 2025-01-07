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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentGalleryBinding
import com.escom7cv1.proyectotodo.databinding.FragmentSlideshowBinding
import com.escom7cv1.proyectotodo.ui.gallery.GalleryViewModel
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModel
import com.escom7cv1.proyectotodo.ui.tarea.Tarea
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModel
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModelFactory

class SlideshowFragment : Fragment() {

  private var _binding: FragmentGalleryBinding? = null
  private val binding get() = _binding!!

  private lateinit var tareaViewModel: TareaViewModel
  var nopacoins = 20

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val galleryViewModel =
      ViewModelProvider(this).get(GalleryViewModel::class.java)

    _binding = FragmentGalleryBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val database = AppDatabase.getDatabase(requireContext())
    val repository = TareaRepository(database)
    val factory = TareaViewModelFactory(repository)
    tareaViewModel = ViewModelProvider(this, factory).get(TareaViewModel::class.java)

    tareaViewModel.tareasImportantes.observe(viewLifecycleOwner) { tareas ->
      actualizarTareas(tareas)
    }

    tareaViewModel.getTareasImportantes()

    val textView: TextView = binding.textGallery

    galleryViewModel.text.observe(viewLifecycleOwner) {
      textView.text = "Importante"
    }

    /*
    binding.aniadirTarea.setOnClickListener {
      val bundle = Bundle().apply {
        putLong("listaId", 2)
      }
      findNavController().navigate(R.id.nav_crearTarea, bundle)
    }*/

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun actualizarTareas(tareas: List<Tarea>) {
    val contenedorTareas: LinearLayout = binding.contenedorTarea
    contenedorTareas.removeAllViews()

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
        ).apply {
          leftMargin = 30
          bottomMargin = 40
        }
        setOnCheckedChangeListener { _, isChecked ->
          showConfirmationDialog(isChecked, this, tarea)
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
  }

  private fun showConfirmationDialog(isChecked: Boolean, checkBox: CheckBox, tarea: Tarea) {
    val message = if (isChecked) {
      "¿Estás seguro de marcar como FINALIZADA esta tarea?"
    } else {
      "¿Estás seguro de desmarcar esta tarea?"
    }

    val dialog = AlertDialog.Builder(requireContext())
      .setTitle("Confirmar acción")
      .setMessage(message)
      .setPositiveButton("Sí") { dialog, _ ->
        Toast.makeText(context, "Tarea completada: ${tarea.nombre}", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
        finalizarTarea(tarea)
      }
      .setNegativeButton("No") { dialog, _ ->
        checkBox.isChecked = !isChecked
        dialog.dismiss()
      }
      .create()

    checkBox.setOnCheckedChangeListener(null)
    dialog.show()

    dialog.setOnDismissListener {
      checkBox.setOnCheckedChangeListener { _, newIsChecked ->
        showConfirmationDialog(newIsChecked, checkBox, tarea)
      }
    }
  }

  private fun finalizarTarea(tarea: Tarea) {
    nopacoins += 20

    val bundle = Bundle().apply {
      putString("nombre", tarea.nombre)
      putInt("nopacoins", nopacoins)
    }
    findNavController().navigate(R.id.nav_tareaCompletada, bundle)

    tareaViewModel.updateStatusTarea(tarea.id, true)
    tareaViewModel.getTareasImportantes()  // Refresh the list
  }
}