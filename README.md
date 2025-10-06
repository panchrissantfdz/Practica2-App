# PRACTICA 2 - SHARED PREFERENCES



## Objetivo del Proyecto

Esta aplicación Android fue desarrollada para demostrar dos objetivos principales:

1.  Una **navegación jerárquica inmersiva** de tres niveles con transiciones avanzadas (Shared Element Transitions).
2.  La implementación de **temas dinámicos (Claro/Oscuro)** con persistencia de usuario utilizando `SharedPreferences` para garantizar la coherencia visual en toda la aplicación.

### Jerarquía de Navegación

* **Nivel 1:** Selección de Deporte (`SportSelectionActivity`)
* **Nivel 2:** Lista de Atletas (`AthleteListActivity`)
* **Nivel 3:** Detalle del Momento Clave (`MomentDetailActivity`)

---

## ⚙️ Implementación de Temas y Persistencia

La aplicación soporta dos esquemas de color principales: `Theme.App.Light` y `Theme.App.Dark`.

### Lógica de Persistencia

1.  **Activación:** En la `SportSelectionActivity` (Nivel 1), un **`Switch`** permite al usuario alternar entre el tema.
2.  **Almacenamiento:** Al cambiar el estado del `Switch`, la preferencia (`isDarkModeEnabled`) se guarda inmediatamente en **`SharedPreferences`**.
3.  **Aplicación Consistente:** En el método `onCreate()` de **cada una de las tres Activities**, la preferencia se lee **antes** de `super.onCreate()` y `setContentView()`.
    * Esto asegura que el tema se aplique correctamente al iniciar la Activity, respetando la última elección del usuario.
    * El fondo principal de cada Activity se define como `android:background="?android:colorBackground"` en el XML, permitiendo que el tema controle el color de fondo de manera dinámica.

### Requisitos de Persistencia Cumplidos

* [x] Definición clara de dos temas (`Theme.App.Light` y `Theme.App.Dark`).
* [x] Uso correcto de `SharedPreferences` para guardar y recuperar la preferencia del tema.
* [x] El tema se aplica a **todas las Activities** de la jerarquía.
* [x] La elección del tema es **persistente** entre sesiones (sobrevive al cierre completo de la aplicación).

---

## 🎨 Decisiones de Diseño y Transiciones Inmersivas

### Transiciones Principales

| Transición | Tipo de Animación | Propósito |
| :--- | :--- | :--- |
| **Nivel 2 $\to$ Nivel 3** | **Shared Element Transition** | La imagen y el nombre del atleta viajan desde la tarjeta del `RecyclerView` hasta la cabecera del detalle, creando un efecto de zoom profesional. |
| **Activities $\to$ Fragments** | **`SLIDE-UP` Animation** | El `WelcomeFragment` y el `StatsFragment` aparecen deslizando desde la parte inferior, ofreciendo información adicional sin romper el flujo de la Activity. |

### Componentes Inmersivos

* **Puntos de Interés:** Están implementados en los tres niveles (botones de deporte, tarjetas de atleta y botón de estadísticas).
* **Fondo Dinámico:** El fondo de cada Activity principal cambia entre un gris claro o un negro profundo (`#121212`) para adaptarse al tema seleccionado.

---

##  Capturas de Pantalla


