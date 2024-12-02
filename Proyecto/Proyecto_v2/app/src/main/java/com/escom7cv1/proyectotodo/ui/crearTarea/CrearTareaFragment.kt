package com.escom7cv1.proyectotodo.ui.crearTarea

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.escom7cv1.proyectotodo.databinding.FragmentCreartareaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CrearTareaFragment : Fragment() {

    private var _binding: FragmentCreartareaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val creartareaViewModel =
            ViewModelProvider(this).get(CreartareaViewModel::class.java)

        _binding = FragmentCreartareaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tituloTarea: EditText = binding.tituloTarea
        val urgenteCheckBox: CheckBox = binding.urgente
        val importanteCheckBox: CheckBox = binding.importante
        val fechaInicioTarea: EditText = binding.fechaInicioTarea
        val fechaFinTarea: EditText = binding.fechaFinTarea
        val guardarTareaBoton: Button = binding.GuardarTarea

        guardarTareaBoton.setOnClickListener {
            val tareaTitulo = tituloTarea.text.toString()
            val isUrgente = urgenteCheckBox.isChecked
            val isImportante = importanteCheckBox.isChecked
            val tareaFechaInicio = fechaInicioTarea.text.toString()
            val tareaFechaFin = fechaFinTarea.text.toString()

            // Aquí se debería guardar la tarea en la base de datos
            if (tareaTitulo.isNotEmpty() && tareaFechaInicio.isNotEmpty() && tareaFechaFin.isNotEmpty()) {

                val tarea = Tarea(
                    titulo = tareaTitulo,
                    urgente = isUrgente,
                    importante = isImportante,
                    fechaInicio = tareaFechaInicio,
                    fechaFin = tareaFechaFin
                )

                guardarTareaBaseDatos(tarea)

                limpiarCampos()

                Toast.makeText(requireContext(), "Tarea '$tareaTitulo' guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    private fun guardarTareaBaseDatos(tarea: Tarea) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase
                .database(requireContext())

            db.tareaDao().insert(tarea)
        }
    }

    private fun limpiarCampos() {
        binding.tituloTarea.text.clear()
        binding.urgente.isChecked = false
        binding.importante.isChecked = false
        binding.fechaInicioTarea.text.clear()
        binding.fechaFinTarea.text.clear()
    }

}


class CreartareaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is crearTarea Fragment"
    }
    val text: LiveData<String> = _text

}


data class Tarea(
    val titulo: String,
    val urgente: Boolean,
    val importante: Boolean,
    val fechaInicio: String,
    val fechaFin: String
)

@Dao
interface TareaDao {
    @Insert
    suspend fun insert(tarea: Tarea)

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)

    @Query("SELECT * FROM tarea")
    fun getAll(): LiveData<List<Tarea>>
}

@Database(entities = [Tarea::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tareaDao(): TareaDao

    companion object {
        fun database(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "tarea-db"
            ).build()
        }
    }
}


