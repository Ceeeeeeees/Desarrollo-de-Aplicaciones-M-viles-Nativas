package com.escom7cv1.proyectotodo.ui.listaTareas

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.escom7cv1.proyectotodo.ui.gallery.GalleryViewModel
import com.escom7cv1.proyectotodo.ui.lista.ListaRepository
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModel
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModelFactory
import com.escom7cv1.proyectotodo.ui.planta.PlantaRepository
import com.escom7cv1.proyectotodo.ui.planta.PlantaViewModel
import com.escom7cv1.proyectotodo.ui.planta.PlantaViewModelFactory
import com.escom7cv1.proyectotodo.ui.tarea.Tarea
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModel
import com.escom7cv1.proyectotodo.ui.tarea.TareaViewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaTareasFragment : Fragment() {

    private var listaId: Long? = null
    private var nombreLista: String? = null

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var tareaViewModel: TareaViewModel
    private lateinit var plantaViewModel: PlantaViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listaId = arguments?.getLong("listaId")
        nombreLista = arguments?.getString("nombreLista")

        val database = AppDatabase.getDatabase(requireContext())
        val repository = TareaRepository(database)
        val factory = TareaViewModelFactory(repository)
        tareaViewModel = ViewModelProvider(this, factory).get(TareaViewModel::class.java)

        val listaRepository = ListaRepository(database)
        val listaFactory = ListaViewModelFactory(listaRepository)
        val listaViewModel = ViewModelProvider(this, listaFactory).get(ListaViewModel::class.java)

        val repository2 = PlantaRepository(database)
        val factory2 = PlantaViewModelFactory(repository2)
        plantaViewModel = ViewModelProvider(this, factory2).get(PlantaViewModel::class.java)

        tareaViewModel.tareas.observe(viewLifecycleOwner) { tareas ->
            actualizarTareas(tareas)
        }

        tareaViewModel.getTareasPorLista(listaId!!)

        val textView: TextView = binding.textGallery

        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = "Lista de " + nombreLista
        }

        binding.aniadirTarea.setOnClickListener {
            val bundle = Bundle().apply {
                putLong("listaId", listaId!!)
            }
            findNavController().navigate(R.id.nav_crearTarea, bundle)
        }

        binding.eliminarLista.setOnClickListener {
            if (listaId != null) {
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Confirmar acción")
                    .setMessage("¿Estás seguro de eliminar la lista $nombreLista?")
                    .setPositiveButton("Sí") { dialog, _ ->
                        listaViewModel.eliminarListaById(listaId!!)
                        Toast.makeText(context, "Lista eliminada: $nombreLista", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                dialog.show()
            }
        }

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
        val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
            Log.e("CoroutineExceptionHandler", "Error capturado: ${throwable.message}")
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch() {
            val planta = plantaViewModel.getPuntos();
            var nopacoins = planta.puntos
            nopacoins += 20

            if (nopacoins <= 19) {
                planta.etapaCrecimiento = 1
            } else if (nopacoins in 20..39) {
                planta.etapaCrecimiento = 2
            } else if (nopacoins in 40..59) {
                planta.etapaCrecimiento = 3
            } else if (nopacoins in 60..79) {
                planta.etapaCrecimiento = 4
            } else if (nopacoins in 80..99)  {
                planta.etapaCrecimiento = 5
            } else {
                planta.etapaCrecimiento = 5
            }

            planta.puntos = nopacoins

            plantaViewModel.updatePuntos(planta)
            tareaViewModel.updateStatusTarea(tarea.id, true)
            tareaViewModel.getTareasPorLista(listaId!!)
            withContext(Dispatchers.Main) {
                val bundle = Bundle().apply {
                    putString("nombre", tarea.nombre)
                    putInt("nopacoins", nopacoins)
                }
                findNavController().navigate(R.id.nav_tareaCompletada, bundle)
            }
        }
    }
}