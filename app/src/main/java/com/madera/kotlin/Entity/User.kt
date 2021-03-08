package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val idUser: Long,
    @ColumnInfo(name = "refUser") val refUser: Long,
    @ColumnInfo(name = "pseudoUser") val pseudoUser: String,
    @ColumnInfo(name = "passwordUser") val passwordUser: String,
    @ColumnInfo(name = "nameUser") val nameUser: String,
    @ColumnInfo(name = "firstNameUser") val firstNameUser: String,
    @ColumnInfo(name = "roleUser") val roleUser: Int,
)
