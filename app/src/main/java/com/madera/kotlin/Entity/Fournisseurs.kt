package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Fournisseurs(
    @PrimaryKey var id: Int,
    val nom: String?,
    val adresse: String?,
    val nom_contact: String?,
    val numero_tel: String?

)