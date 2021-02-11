package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chantier(
    @PrimaryKey var id: Int,
    val nom: String?,
    val notes: String?
)
