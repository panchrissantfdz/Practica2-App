package com.tarea.activities

// Modelo de datos para la lista de atletas (Nivel 2)
data class Athlete(
    val name: String,
    val imageResId: Int,
    val id: String
)

// Modelo de datos para el detalle del momento clave (Nivel 3)
data class MomentDetail(
    val athleteName: String,     // 1. Campo para el nombre del atleta
    val title: String,           // 2. Título del momento
    val description: String,     // 3. Descripción
    val videoId: String,         // 4. ID de YouTube
    val achievementText: String  // 5. Estadísticas
)