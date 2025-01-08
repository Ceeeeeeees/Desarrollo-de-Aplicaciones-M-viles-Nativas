package com.escom7cv1.proyectotodo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.escom7cv1.proyectotodo.R
import androidx.lifecycle.ViewModelProvider

import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.databinding.FragmentHomeBinding
import com.escom7cv1.proyectotodo.ui.planta.Planta
import com.escom7cv1.proyectotodo.ui.tarea.TareaRepository

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

        //Obtener la base de datos
        val database = AppDatabase.getDatabase(requireContext())
        val plantaDao = database.plantaDao()
        val repository = HomeRepository(database.plantaDao())

        val factory = HomeViewModelFactory(repository)
        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Obtener los elementos de la vista
        homeViewModel.planta.observe(viewLifecycleOwner) { planta ->
            val imageView = binding.planta
            val drawableRes = when (planta.etapaCrecimiento) {
                1 -> R.drawable.semilla_1
                2 -> R.drawable.semilla_2
                3 -> R.drawable.semilla_3
                4 -> R.drawable.plantita_1
                5 -> R.drawable.plantita_2
                else -> R.drawable.planta
            }
            imageView.setImageResource(drawableRes)
        }
      return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}