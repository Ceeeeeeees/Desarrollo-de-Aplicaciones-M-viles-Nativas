package com.escom7cv1.proyectotodo.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.escom7cv1.proyectotodo.databinding.FragmentListaBinding

class ListaAgregar : Fragment() {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listaViewModel =
            ViewModelProvider(this).get(ListaViewModel::class.java)

        _binding = FragmentListaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBienvenida
        listaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
