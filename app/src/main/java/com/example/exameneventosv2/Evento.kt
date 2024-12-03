package com.example.exameneventosv2

data class Evento(
    val nombre: String = "",
    val descripcion: String = "",
    val direccion: String = "",
    val precio: Double = 0.0,
    val fecha: String = "",
    val aforo: Int = 0
)