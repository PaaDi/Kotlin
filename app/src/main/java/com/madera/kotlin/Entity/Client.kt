package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey var id: Int,
    val nom: String?,
    val prenom: String?,
    val adresse: String?,
    val numero_tel: String?,
    val entreprise: String?
)