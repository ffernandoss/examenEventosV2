package com.example.exameneventosv2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RegistroEventosActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_eventos)

        db = FirebaseFirestore.getInstance()

        val editTextNombre: EditText = findViewById(R.id.editTextNombre)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion)
        val editTextDireccion: EditText = findViewById(R.id.editTextDireccion)
        val editTextPrecio: EditText = findViewById(R.id.editTextPrecio)
        val editTextFecha: EditText = findViewById(R.id.editTextFecha)
        val editTextAforo: EditText = findViewById(R.id.editTextAforo)
        val buttonAceptar: Button = findViewById(R.id.buttonAceptar)
        val buttonCancelar: Button = findViewById(R.id.buttonCancelar)

        buttonAceptar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()
            val direccion = editTextDireccion.text.toString()
            val precio = editTextPrecio.text.toString().toDoubleOrNull()
            val fecha = editTextFecha.text.toString()
            val aforo = editTextAforo.text.toString().toIntOrNull()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && direccion.isNotEmpty() && precio != null && fecha.isNotEmpty() && aforo != null) {
                val evento = Evento(nombre, descripcion, direccion, precio, fecha, aforo)
                db.collection("eventos")
                    .add(evento)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Evento registrado", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al registrar evento", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancelar.setOnClickListener {
            finish()
        }
    }
}