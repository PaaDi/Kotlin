package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Login(
    @PrimaryKey var id: Int,
    val pseudoUser: String?,
    val passwordUser: String?,
    val nameUser: String?,
    val firstNameUser: String?,
    val mailUser: String?,
    val roleUser: Int
)