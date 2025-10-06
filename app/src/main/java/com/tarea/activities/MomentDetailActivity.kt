package com.tarea.activities




import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MomentDetailActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "ThemePrefs"
    private val KEY_DARK_MODE = "isDarkModeEnabled"

    private var currentAchievementText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        applyTheme()
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_moment_detail)
        startPostponedEnterTransition()

        val athleteId = intent.getStringExtra("EXTRA_ATHLETE_ID") ?: return

        // 1. Asignar nombres de transición dinámicos (CRÍTICO)
        findViewById<ImageView>(R.id.img_athlete_detail).transitionName = "athlete_image_transition_${athleteId}"

        // El ID 'tv_athlete_name_detail' está correcto si lo tienes en tu XML
        findViewById<TextView>(R.id.tv_athlete_name_detail).transitionName = "athlete_name_transition_${athleteId}"

        // 2. Cargar Datos y Actualizar UI
        val detail = getMomentDetail(athleteId)

        findViewById<TextView>(R.id.tv_moment_title).text = detail.title

        // 🚨 CORRECCIÓN 1: El ID correcto es tv_moment_description, NO tv_mmoment_description
        findViewById<TextView>(R.id.tv_moment_description).text = detail.description

        // 🚨 CORRECCIÓN 2: 'athleteName' es ahora un campo válido en 'detail'
        findViewById<TextView>(R.id.tv_athlete_name_detail).text = detail.athleteName

        // Simular carga de imagen
        // ...

        currentAchievementText = detail.achievementText

        // 3. Configurar el Fragment de Estadísticas (Punto de Interés)
        // 🚨 CORRECCIÓN 3: El ID es btn_show_stats_fragment (quitando el error 'Unresolved reference')
        findViewById<Button>(R.id.btn_show_stats_fragment).setOnClickListener {
            showStatsFragment()
        }
    }

    /**
     * Simula la obtención de los detalles del momento basado en el ID del atleta.
     */
    private fun getMomentDetail(athleteId: String): MomentDetail {
        return when (athleteId) {
            "messi_2022" -> MomentDetail(
                athleteName = "Lionel Messi", // Constructor con 5 argumentos
                title = "El Clímax Mundial",
                description = "El momento en que Lionel Messi levantó la Copa del Mundo en 2022...",
                videoId = "youtube_id_messi",
                achievementText = "Máximo goleador histórico con 800+ goles.\n7 Balones de Oro."
            )
            "jordan_lastshot" -> MomentDetail(
                athleteName = "Michael Jordan", // Constructor con 5 argumentos
                title = "The Last Dance",
                description = "La icónica canasta final de Michael Jordan contra los Jazz en 1998...",
                videoId = "youtube_id_jordan",
                achievementText = "6 Títulos de la NBA.\n5 veces MVP de la Temporada."
            )
            // 🚨 CORRECCIÓN 4: El caso 'else' debe coincidir con el constructor de 5 argumentos
            else -> MomentDetail(
                athleteName = "Desconocido",
                title = "N/A",
                description = "Momento No Encontrado",
                videoId = "",
                achievementText = "Información no disponible."
            )
        }
    }

    /**
     * Muestra el StatsFragment con la transición SLIDE-UP.
     */
    private fun showStatsFragment() {
        StatsFragment.newInstance(currentAchievementText).show(
            supportFragmentManager,
            "StatsFragmentTag"
        )
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