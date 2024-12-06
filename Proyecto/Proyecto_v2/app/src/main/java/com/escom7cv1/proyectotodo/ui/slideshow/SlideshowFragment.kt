package com.escom7cv1.proyectotodo.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.escom7cv1.proyectotodo.R
import com.escom7cv1.proyectotodo.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

private var _binding: FragmentSlideshowBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

    _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textSlideshow
    slideshowViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

    val aniadirTareaBoton: Button = binding.aniadirTareaImportante

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