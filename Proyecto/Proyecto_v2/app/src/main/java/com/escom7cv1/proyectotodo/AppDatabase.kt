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


@Database(entities = [Lista::class, Tarea::class, Planta::class, Usuario::class], version = 1)
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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}