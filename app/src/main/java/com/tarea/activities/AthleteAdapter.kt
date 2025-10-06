package com.tarea.activities



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 🚨 CORRECCIÓN: Se elimina la palabra clave 'abstract'
class AthleteAdapter(
    private val athleteList: List<Athlete>,
    private val clickListener: (Athlete, View, View) -> Unit
) : RecyclerView.Adapter<AthleteAdapter.AthleteViewHolder>() {

    class AthleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_athlete)
        val nameTextView: TextView = itemView.findViewById(R.id.tv_athlete_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthleteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_athlete_card, parent, false)
        return AthleteViewHolder(view)
    }

    override fun onBindViewHolder(holder: AthleteViewHolder, position: Int) {
        val athlete = athleteList[position]

        // 1. Establecer datos visuales
        holder.nameTextView.text = athlete.name
        holder.imageView.setImageResource(athlete.imageResId)

        // 2. Aseguramos que la vista del nombre y la imagen tienen nombres de transición dinámicos
        holder.nameTextView.transitionName = "athlete_name_transition_${athlete.id}"
        holder.imageView.transitionName = "athlete_image_transition_${athlete.id}"

        // 3. Configurar el clic para la transición creativa al Nivel 3
        holder.itemView.setOnClickListener {
            clickListener(athlete, holder.imageView, holder.nameTextView)
        }
    }

    override fun getItemCount() = athleteList.size
}