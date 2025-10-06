package com.tarea.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

/**
 * Fragmento que muestra la información de bienvenida de la aplicación.
 */
class WelcomeFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Estilo para el Fragment: Animación de SLIDE-UP para la transición creativa
        setStyle(STYLE_NORMAL, R.style.AppTheme_Dialog_Slide)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento (debes crear 'fragment_welcome.xml')
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Lógica para cerrar el fragmento
        view.findViewById<Button>(R.id.btn_close_welcome).setOnClickListener {
            dismiss()
        }
    }
}