package com.tarea.activities



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

/**
 * Fragmento que muestra estadísticas clave o logros asociados al momento.
 */
class StatsFragment : DialogFragment() {

    // Clave para pasar el texto del logro/estadística
    companion object {
        private const val ARG_ACHIEVEMENT = "achievement_text"
        fun newInstance(achievement: String): StatsFragment {
            val fragment = StatsFragment()
            val args = Bundle()
            args.putString(ARG_ACHIEVEMENT, achievement)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Usamos el mismo estilo de animación SLIDE-UP del Nivel 1
        // Inside StatsFragment.kt
        setStyle(STYLE_NORMAL, R.style.AppTheme_Dialog_Slide)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento (crear 'fragment_stats.xml')
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val achievementText = arguments?.getString(ARG_ACHIEVEMENT) ?: "Logro no disponible."

        view.findViewById<TextView>(R.id.tv_stats_content).text = achievementText

        // Botón de cierre
        view.findViewById<View>(R.id.btn_close_stats).setOnClickListener {
            dismiss()
        }
    }
}