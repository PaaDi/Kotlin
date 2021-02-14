package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "composant")
data class Composants(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nomComposant") val nomComposant: String,
    @ColumnInfo(name = "categorieComposant") val categorieComposant: String,
    @ColumnInfo(name = "prixComposant") val prixComposant: Float,
)
