package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Gamme(
    @PrimaryKey var id: Int,
    val nom: String?,
    val sans_angle: String?,
    val angle_ouvrant: String?,
    val angle_fermant: String?

)