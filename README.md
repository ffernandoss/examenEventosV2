https://github.com/ffernandoss/examenEventosV2.git


# ExamenEventosV2

Este proyecto es una aplicación de Android para registrar y mostrar eventos utilizando Firebase Firestore.

## Clases

### MainActivity

`MainActivity` es la actividad principal que carga el `MainFragment` al iniciar la aplicación.

#### Métodos

- `onCreate(savedInstanceState: Bundle?)`: Configura el contenido de la actividad y carga el `MainFragment` si no hay un estado guardado.

### MainFragment

`MainFragment` es el fragmento principal que muestra una lista de eventos y un botón para registrar nuevos eventos.

#### Métodos

- `onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)`: Infla el diseño del fragmento.
- `onViewCreated(view: View, savedInstanceState: Bundle?)`: Configura el botón de registro y el `RecyclerView` para mostrar los eventos.
- `showEventDetails(evento: Evento)`: Muestra un `AlertDialog` con los detalles del evento seleccionado.
- `onDestroyView()`: Elimina el listener de Firestore cuando se destruye la vista.

### RegistroEventosActivity

`RegistroEventosActivity` es la actividad para registrar nuevos eventos.

#### Métodos

- `onCreate(savedInstanceState: Bundle?)`: Configura el contenido de la actividad y maneja los clics de los botones para aceptar o cancelar el registro de eventos.

### EventAdapter

`EventAdapter` es el adaptador para el `RecyclerView` que muestra la lista de eventos.

#### Métodos

- `onCreateViewHolder(parent: ViewGroup, viewType: Int)`: Infla el diseño del elemento de la lista y crea un `EventViewHolder`.
- `onBindViewHolder(holder: EventViewHolder, position: Int)`: Vincula los datos del evento al `EventViewHolder`.

### EventViewHolder

`EventViewHolder` es el `ViewHolder` para los elementos del `RecyclerView`.

#### Métodos

- `bind(evento: Evento)`: Vincula los datos del evento a las vistas del elemento de la lista.

### EventDiffCallback

`EventDiffCallback` es una clase para comparar elementos en el `RecyclerView`.

#### Métodos

- `areItemsTheSame(oldItem: Evento, newItem: Evento)`: Comprueba si dos elementos son los mismos.
- `areContentsTheSame(oldItem: Evento, newItem: Evento)`: Comprueba si el contenido de dos elementos es el mismo.

### Evento

`Evento` es una clase de datos que representa un evento.

#### Propiedades

- `nombre: String`
- `descripcion: String`
- `direccion: String`
- `precio: Double`
- `fecha: String`
- `aforo: Int`

### RegistroEventosFragment

`RegistroEventosFragment` es un fragmento para registrar nuevos eventos (actualmente no utilizado).

#### Métodos

- `onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)`: Infla el diseño del fragmento.

## Layouts

### activity_main.xml

Contiene un `FrameLayout` que actúa como contenedor para los fragmentos.

### fragment_main.xml

Contiene un `TextView` para el título, un `Button` para registrar eventos y un `RecyclerView` para mostrar la lista de eventos.

### activity_registro_eventos.xml

Contiene varios `EditText` para ingresar los detalles del evento y dos `Button` para aceptar o cancelar el registro.

### fragment_registro_eventos.xml

Contiene un `TextView` para el título del registro de eventos.


## Cambio de Idioma

Para alternar entre inglés y español en la aplicación, se ha implementado un botón que cambia el idioma de la interfaz. Al pulsar el botón, la aplicación cambiará el idioma y actualizará los textos de los botones y otros elementos de la interfaz.

### Implementación

1. **Definición de cadenas de texto en `strings.xml` y `strings-en.xml`:**

    `res/values/strings.xml`:
    ```xml
    <resources>
        <string name="app_name">ExamenEventosV2</string>
        <string name="eventos">Eventos</string>
        <string name="registrar">Registrar</string>
        <string name="ingles">Inglés</string>
        <string name="espanol">Español</string>
        <!-- Otros textos -->
    </resources>
    ```

    `res/values-en/strings.xml`:
    ```xml
    <resources>
        <string name="app_name">ExamenEventosV2</string>
        <string name="eventos">Events</string>
        <string name="registrar">Register</string>
        <string name="ingles">English</string>
        <string name="espanol">Spanish</string>
        <!-- Otros textos traducidos -->
    </resources>
    ```

2. **Modificación de `MainActivity` para alternar entre idiomas:**

    ```kotlin
    package com.example.exameneventosv2

    import android.content.Intent
    import android.content.res.Configuration
    import android.os.Bundle
    import android.widget.Button
    import androidx.appcompat.app.AppCompatActivity
    import java.util.Locale

    class MainActivity : AppCompatActivity() {
        private lateinit var buttonChangeLanguage: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment())
                    .commit()
            }

            buttonChangeLanguage = findViewById(R.id.buttonChangeLanguage)
            buttonChangeLanguage.setOnClickListener {
                val currentLocale = resources.configuration.locale
                if (currentLocale.language == "en") {
                    setLocale("es")
                    buttonChangeLanguage.text = getString(R.string.ingles)
                } else {
                    setLocale("en")
                    buttonChangeLanguage.text = getString(R.string.espanol)
                }
            }
        }

        private fun setLocale(languageCode: String) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)

            // Reiniciar la actividad para aplicar los cambios de idioma
            val refresh = Intent(this, MainActivity::class.java)
            startActivity(refresh)
            finish()
        }
    }
    ```

Con estos cambios, al pulsar el botón de cambio de idioma, la aplicación alternará entre inglés y español, actualizando los textos de los botones y otros elementos de la interfaz.
