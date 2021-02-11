package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Devis(
    @PrimaryKey var id: Int,
    val nom: String?,
    val etat: String?,
    val date_creation: Date?,
    val remise: Float?,
    val prixHT: Float?,
    val taux_TVA: Float?,
    val m_lineaire: Float?,
    val document: String?,
    val notes: String?

)