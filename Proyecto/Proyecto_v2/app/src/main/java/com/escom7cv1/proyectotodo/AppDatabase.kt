package com.escom7cv1.proyectotodo


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.escom7cv1.proyectotodo.ui.lista.Lista
import com.escom7cv1.proyectotodo.ui.lista.ListaDao
import com.escom7cv1.proyectotodo.ui.planta.Planta
import com.escom7cv1.proyectotodo.ui.planta.PlantaDao
import com.escom7cv1.proyectotodo.ui.tarea.Tarea
import com.escom7cv1.proyectotodo.ui.tarea.TareaDao
import com.escom7cv1.proyectotodo.ui.usuario.Usuario
import com.escom7cv1.proyectotodo.ui.usuario.UsuarioDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Lista::class, Tarea::class, Planta::class, Usuario::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listaDao(): ListaDao
    abstract fun tareaDao(): TareaDao
    abstract fun plantaDao(): PlantaDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val DATABASE_NAME = "app_database"

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                //context.deleteDatabase("app_database")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                CoroutineScope(Dispatchers.IO).launch {
                    initializeDatabase(context, instance)
                }
                instance
            }
        }

        private suspend fun initializeDatabase(context: Context, database: AppDatabase) {
            withContext(Dispatchers.IO) {
                val listaDao = database.listaDao()
                val plantaDao = database.plantaDao()
                val usuarioDao = database.usuarioDao()
                CoroutineScope(Dispatchers.IO).launch {
                    val listas = listaDao.getListasNoDefault().size
                    if (listas == 0) {
                        listaDao.insertLista(Lista(nombre = "Mi día", isDefault = true))
                        listaDao.insertLista(Lista(nombre = "Importante", isDefault = true))
                    }
                    val user = Usuario(1,0,1)
                    val plantas = plantaDao.obtenerTodasLasPlantas() ?: emptyList()
                    if (plantas.isEmpty()) {
                        plantaDao.insertPlanta(Planta(etapaCrecimiento = 1))
                    }
                    //usuarioDao.insertUser(user)
                }
            }
        }

        private suspend fun clearAllData(database: AppDatabase) {
            database.listaDao().deleteAllListas()
            database.tareaDao().deleteAllTareas()
        }
    }
}