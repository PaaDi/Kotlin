package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fournisseur")
data class Fournisseurs(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nomFournisseur") val nomFournisseur: String,
    @ColumnInfo(name = "adresseFournisseur") val adresseFournisseur: String,
    @ColumnInfo(name = "nomcontactFournisseur") val nomcontactFournisseur: String,
    @ColumnInfo(name = "numerotelFournisseur") val numerotelFournisseur: String,
)
