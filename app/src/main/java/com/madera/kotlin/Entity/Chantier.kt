package com.madera.kotlin.Entity

data class Chantier(
    val idChantier: Int,
    val nom: String,
    val idProjet: Int,
    val idUser: Int,
    val notes: String,
)