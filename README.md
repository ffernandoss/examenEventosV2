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
