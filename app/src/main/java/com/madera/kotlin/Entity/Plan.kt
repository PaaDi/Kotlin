package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Plan(
    @PrimaryKey var id: Int,
    val taille_x: Float?,
    val taille_y: Float?,
    val nbe_etages: Int
)