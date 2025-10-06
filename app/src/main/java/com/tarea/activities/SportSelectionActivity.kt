package com.tarea.activities // Asegúrate de que este sea tu paquete correcto

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch // Importar la clase Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat

class SportSelectionActivity : AppCompatActivity() {

    // Variables de SharedPreferences para persistencia
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "ThemePrefs"
    private val KEY_DARK_MODE = "isDarkModeEnabled"

    override fun onCreate(savedInstanceState: Bundle?) {

        // 🚨 LÓGICA DE TEMA (Paso 1: Cargar y Aplicar)
        // 1. Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // 2. Aplicar el tema ANTES de super.onCreate() y setContentView()
        applyTheme()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_selection)

        // Enlazar los botones (Puntos de Interés)
        val btnFutbol: Button = findViewById(R.id.btn_futbol)
        val btnBaloncesto: Button = findViewById(R.id.btn_baloncesto)
        val btnTenis: Button = findViewById(R.id.btn_tenis)
        val btnInfo: ImageButton = findViewById(R.id.button_info)

        // Enlazar el Switch de Temas
        val themeSwitch: Switch = findViewById(R.id.theme_switch)

        // 🚨 LÓGICA DE TEMA (Paso 2: Control del Switch)
        // 1. Establecer el estado inicial del Switch
        themeSwitch.isChecked = sharedPreferences.getBoolean(KEY_DARK_MODE, false)

        // 2. Configurar el listener para guardar y aplicar el cambio de tema
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar la nueva preferencia
            sharedPreferences.edit().putBoolean(KEY_DARK_MODE, isChecked).apply()

            // Recrear la actividad para aplicar el tema de inmediato
            // Esto vuelve a llamar a onCreate, donde applyTheme() se ejecuta primero.
            recreate()
        }

        // 1. Configurar los clics para la navegación y transición
        btnFutbol.setOnClickListener { navigateToAthleteList("Fútbol", it) }
        btnBaloncesto.setOnClickListener { navigateToAthleteList("Baloncesto", it) }
        btnTenis.setOnClickListener { navigateToAthleteList("Tenis", it) }

        // 2. Configurar el clic para mostrar el Fragment de Bienvenida
        btnInfo.setOnClickListener {
            // Muestra el Fragmento de Bienvenida como un DialogFragment
            WelcomeFragment().show(supportFragmentManager, "WelcomeFragmentTag")
        }
    }

    /**
     * Aplica el tema (Oscuro o Claro) basado en la preferencia guardada en SharedPreferences.
     * Debe llamarse antes de super.onCreate().
     */
    private fun applyTheme() {
        // Lee la preferencia guardada (false por defecto, que es el Tema Claro)
        val isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)

        // Aplica el tema correspondiente usando los estilos definidos en themes.xml
        if (isDarkMode) {
            setTheme(R.style.Theme_App_Dark)
        } else {
            setTheme(R.style.Theme_App_Light)
        }
    }


    /**
     * Navega a la Activity de la lista de atletas con una Transición Creativa de Shared Element Transition.
     * @param sportName El deporte seleccionado.
     * @param sharedElement El botón que se usará como elemento compartido en la transición.
     */
    private fun navigateToAthleteList(sportName: String, sharedElement: View) {
        val intent = Intent(this, AthleteListActivity::class.java).apply {
            putExtra("EXTRA_SPORT_NAME", sportName) // Pasa el dato
        }

        // Usaremos el mismo nombre de transición definido en activity_sport_selection.xml
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            sharedElement,
            sharedElement.transitionName // Obtenemos el nombre "sport_transition" directamente de la vista
        )

        startActivity(intent, options.toBundle())
    }
}