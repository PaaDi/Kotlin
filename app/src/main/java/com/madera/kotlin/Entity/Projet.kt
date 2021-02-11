package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Projet(
    @PrimaryKey var id: Int,
    val nom: String?,
    val dateCreation: Date,
    val notes: String?
)