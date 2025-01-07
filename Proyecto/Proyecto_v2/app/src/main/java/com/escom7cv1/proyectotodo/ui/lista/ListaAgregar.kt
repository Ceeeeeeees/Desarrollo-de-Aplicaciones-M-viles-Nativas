package com.escom7cv1.proyectotodo.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.escom7cv1.proyectotodo.AppDatabase
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentListaBinding

class ListaAgregar : Fragment() {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaViewModel: ListaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)

        val database = AppDatabase.getDatabase(requireContext())
        val repository = ListaRepository(database)
        val factory = ListaViewModelFactory(repository)
        listaViewModel = ViewModelProvider(this, factory).get(ListaViewModel::class.java)

        val btnAceptar: Button = binding.btnAceptarLista
        val btnCancelar:Button = binding.btnCancelarLista

        val textView: TextView = binding.textBienvenida

        btnCancelar.setOnClickListener {
            findNavController().navigate(R.id.nav_home)
        }

        btnAceptar.setOnClickListener {
            val listaNombre = binding.editTextText.text.toString()
            if (listaNombre.isNotBlank()){
                val nuevaLista = Lista(nombre = listaNombre)
                listaViewModel.insertLista(nuevaLista)
                Toast.makeText(context, "Lista creada: $listaNombre", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.nav_home)
            } else {
                Toast.makeText(context, "El nombre de la lista no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
