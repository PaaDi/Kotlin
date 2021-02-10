package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "pseudoUser") val pseudoUser: String,
    @ColumnInfo(name = "passwordUser") val passwordUser: String,
    @ColumnInfo(name = "nameUser") val nameUser: String,
    @ColumnInfo(name = "firstNameUser") val firstNameUser: String,
    @ColumnInfo(name = "roleUser") val roleUser: String,
)
