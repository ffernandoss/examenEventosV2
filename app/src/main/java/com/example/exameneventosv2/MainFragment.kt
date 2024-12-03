package com.example.exameneventosv2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class MainFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var eventAdapter: EventAdapter
    private var eventListener: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonRegistrar: Button = view.findViewById(R.id.buttonRegistrar)
        buttonRegistrar.setOnClickListener {
            val intent = Intent(activity, RegistroEventosActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(context)
        eventAdapter = EventAdapter { evento -> showEventDetails(evento) }
        recyclerView.adapter = eventAdapter

        db = FirebaseFirestore.getInstance()
        eventListener = db.collection("eventos")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val events = snapshots?.documents?.mapIndexed { index, document ->
                    val evento = document.toObject(Evento::class.java)
                    evento?.let {
                        it.copy(nombre = "Evento ${index + 1}")
                    }
                }?.filterNotNull() ?: emptyList()

                eventAdapter.submitList(events)
            }
    }

    private fun showEventDetails(evento: Evento) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(evento.nombre)
        builder.setMessage("Descripción: ${evento.descripcion}\n" +
                "Dirección: ${evento.direccion}\n" +
                "Precio: ${evento.precio}\n" +
                "Fecha: ${evento.fecha}\n" +
                "Aforo: ${evento.aforo}")
        builder.setPositiveButton("OK", null)
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventListener?.remove()
    }
}