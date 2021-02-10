package com.madera.kotlin.Entity

import java.sql.Date

data class Projet(
    val idProjet: Int,
    val nom: String,
    val idClient: Int,
    val dateCreation: Date,
    val notes: String,
)