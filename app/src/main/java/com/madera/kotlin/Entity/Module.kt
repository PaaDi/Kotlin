package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Module(
    @PrimaryKey var id: Int,
    val nom: String?,
    val categorie: String?,
    val unite: String,
    val qte_unite: Int
)