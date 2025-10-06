package com.tarea.activities



import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AthleteListActivity : AppCompatActivity() {

    // ... (onCreate y getAthleteData son correctos)
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "ThemePrefs"
    private val KEY_DARK_MODE = "isDarkModeEnabled"

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        applyTheme()


        super.onCreate(savedInstanceState)
        // Permite que la transición de entrada del Nivel 1 al Nivel 2 se ejecute correctamente
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_athlete_list)

        // Necesario para que Android sepa que debe esperar la carga de vistas para la transición
        window.sharedElementsUseOverlay = false

        val sportName = intent.getStringExtra("EXTRA_SPORT_NAME") ?: "Deporte"
        findViewById<TextView>(R.id.textView_sport_title).text = "LEYENDAS DE $sportName"

        val athleteList = getAthleteData(sportName) // Obtener datos simulados

        val recyclerView: RecyclerView = findViewById(R.id.recycler_athletes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // El adaptador gestionará los clics a la Activity 3
        // 🚨 CORRECCIÓN CLAVE: El clickListener pasa los tres elementos
        val adapter = AthleteAdapter(athleteList) { athlete, sharedImage, sharedName ->
            // La función ahora recibe y utiliza los tres parámetros
            navigateToMomentActivity(athlete, sharedImage, sharedName)
        }
        recyclerView.adapter = adapter

        // Reanudamos la transición una vez que el RecyclerView ha cargado sus elementos visuales
        recyclerView.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun getAthleteData(sport: String): List<Athlete> {
        // Asegúrate de que los IDs y los recursos de imagen sean correctos
        return when (sport) {
            "Fútbol" -> listOf(
                Athlete("Lionel M.", R.drawable.messi, "messi_2022"),
                Athlete("Cristiano R.", R.drawable.ronaldo, "ronaldo_ucl")
            )
            "Baloncesto" -> listOf(
                Athlete("Michael J.", R.drawable.jordan, "jordan_lastshot"),
                Athlete("LeBron J.", R.drawable.lebron, "lebron_comeback")
            )
            else -> emptyList()
        }
    }

    /**
     * Navega a la Activity 3 (Momentos Clave) con una Shared Element Transition.
     */
    private fun navigateToMomentActivity(athlete: Athlete, sharedImage: View, sharedName: View) {

        // El Intent solo necesita los datos del atleta
        val intent = Intent(this, MomentDetailActivity::class.java).apply {
            putExtra("EXTRA_ATHLETE_ID", athlete.id)
            // Ya no es necesario pasar el nombre si el Nivel 3 lo busca por ID,
            // pero lo mantenemos para consistencia.
            putExtra("EXTRA_ATHLETE_NAME", athlete.name)
        }

        // Definir los elementos compartidos y sus nombres de transición DINÁMICOS
        // NOTA: Los nombres de transición deben ser dinámicos y únicos, por eso usamos el athlete.id
        val imagePair = Pair.create(sharedImage, "athlete_image_transition_${athlete.id}")
        val namePair = Pair.create(sharedName, "athlete_name_transition_${athlete.id}")

        // Crear las opciones de transición
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imagePair,
            namePair
        )

        startActivity(intent, options.toBundle())
    }

    private fun applyTheme() {
        val isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        if (isDarkMode) {
            setTheme(R.style.Theme_App_Dark)
        } else {
            setTheme(R.style.Theme_App_Light)
        }
    }
}
