package com.madera.kotlin.Entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    val pseudoUser: String,
    val passwordUser: String,
    val nameUser: String,
    val firstNameUser: String,
    val mailUser: String,
    val roleUser: Int
)