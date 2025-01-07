package com.escom7cv1.proyectotodo
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.escom7cv1.proyectotodo.databinding.ActivityMainBinding
import com.escom7cv1.proyectotodo.ui.lista.ListaRepository
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModel
import com.escom7cv1.proyectotodo.ui.lista.ListaViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaViewModel: ListaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,  R.id.nav_crearTarea), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Inicializar ViewModel de Lista
        val database = AppDatabase.getDatabase(this)
        val repository = ListaRepository(database)
        val factory = ListaViewModelFactory(repository)
        listaViewModel = ViewModelProvider(this, factory).get(ListaViewModel::class.java)


        setUpDynamicMenu(navView, navController)
    }

    private fun setUpDynamicMenu(navView: NavigationView, navController: NavController) {
        listaViewModel.allLists.observe(this) { listas ->
            val navMenu = navView.menu.findItem(R.id.nav_listas_usuario)?.subMenu
            navMenu?.clear()
            listas.forEach { lista ->
                navMenu?.add(R.id.nav_listas_usuario, lista.id.toInt(), 0, lista.nombre)
                    ?.setOnMenuItemClickListener {
                        val bundle = Bundle()
                        bundle.putLong("listaId", lista.id)
                        navController.navigate(R.id.nav_slideshow, bundle)
                        true
                    }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}